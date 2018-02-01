package com.module.ford.smarthealthcar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;


import com.module.ford.smarthealthcar.adapters.GetDataAdapter;
import com.module.ford.smarthealthcar.interfaces.RecyclerViewOnClickListenerHack;
import com.module.ford.smarthealthcar.model.CarData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DetailInfoAct extends AppCompatActivity implements RecyclerViewOnClickListenerHack {

    private RecyclerView mRecyclerView;
    private List<CarData> mListCarData = new ArrayList<>();
    private GetDataAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;


    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    public void initToolbar(){
        setTitle("Collected Data List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_info);

        initToolbar();

        mListCarData = Shared.getInstance().getListCarData() == null ? new ArrayList<CarData>() : Shared.getInstance().getListCarData();


        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);



        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if(dy > 0) //check for scroll down
                {
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (loading)
                    {
                        if ( (visibleItemCount + pastVisiblesItems) >= totalItemCount)
                        {
                            loading = false;
                            Log.v("...", "Last Item Wow !");
                            //Do pagination.. i.e. fetch new data
                        }
                    }
                }

            }
        });



       /* mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager llm = (LinearLayoutManager) mRecyclerView.getLayoutManager();

                GetDataAdapter adapter = (GetDataAdapter) mRecyclerView.getAdapter();

                if (Shared.getInstance().getListCarData().size() == llm.findLastCompletelyVisibleItemPosition() + 1) {
                    List<CarData> listAux = mListCarData;

                    //for (int i = 0; i < listAux.size(); i++) {
                    //    adapter.addListItem(listAux.get(i), mListCarData.size());
                    //}
                }
            }
        });*/
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);



        mAdapter = new GetDataAdapter(this, Shared.getInstance().getListCarData());
        mAdapter.setRecyclerViewOnClickListenerHack(this);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onClickListener(View view, int position) {

        Intent it = new Intent(getApplication(), ItemDetailAct.class);
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Bundle mBundle = new Bundle();
        mBundle.putSerializable("CarData", (Serializable) Shared.getInstance().getListCarData().get(position));
        it.putExtras(mBundle);
        startActivity(it);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        //mAdapter.notifyItemInserted(Shared.getInstance().getListCarData().size() - 1);
        mAdapter.notifyDataSetChanged();
        super.onResume();
    }
}
