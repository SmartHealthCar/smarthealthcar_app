package com.module.ford.smarthealthcar.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.module.ford.smarthealthcar.model.CarData;
import com.module.ford.smarthealthcar.R;
import com.module.ford.smarthealthcar.interfaces.RecyclerViewOnClickListenerHack;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GetDataAdapter extends RecyclerView.Adapter<GetDataAdapter.GetDataViewHolder> {


    private Context mContext;
    private List<CarData> mListCarData;
    private LayoutInflater mLayoutInflater;

    private String CATEGORIA = "AreceberAdapter";
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;
    private float scale;
    private int width;
    private int height;

    public GetDataAdapter(Context _ctx, List<CarData> _ListCarData){
        this.mListCarData = _ListCarData;
        this.mContext = _ctx;

        scale = mContext.getResources().getDisplayMetrics().density;
        width = mContext.getResources().getDisplayMetrics().widthPixels - (int)(14 * scale);
        height = (width / 16) * 9;

        this.mLayoutInflater = (LayoutInflater)_ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public GetDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(CATEGORIA, "onCreateViewHolder()");


        try {

            View v = mLayoutInflater.inflate(R.layout.item_cardata_card, parent, false);
            GetDataViewHolder mvh = new GetDataViewHolder(v);
            return mvh;

        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }



    }

    public void addListItem(CarData c, int position){
        mListCarData.add(c);
        notifyItemInserted(position);
    }

    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack r){
        mRecyclerViewOnClickListenerHack = r;
    }

    @Override
    public void onBindViewHolder(GetDataViewHolder holder, int position) {

        CarData obj = mListCarData.get(position);
        holder.txtVin.setText(obj.getVin() );

        holder.txtFuelLevel.setText(obj.getFuelLevel());

        holder.txtRpm.setText(obj.getRpm());

        holder.txtSpeed.setText( obj.getSpeed() );

        long yourmilliseconds = obj.getTimestamp() ;
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss");
        Date resultdate = new Date(yourmilliseconds);
        holder.txtDt.setText(sdf.format(resultdate));

        holder.txtPrndl.setText(obj.getPrndl());


    }

    @Override
    public int getItemCount() {
        return mListCarData.size();
    }

    public class GetDataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        //public RelativeLayout rrAreceber;
        public TextView txtVin, txtFuelLevel, txtRpm, txtSpeed, txtPrndl, txtDt;

        public GetDataViewHolder(View itemView) {
            super(itemView);

            txtVin = (TextView)itemView.findViewById(R.id.txtVin);
            txtFuelLevel = (TextView)itemView.findViewById(R.id.txtFuelLevel);
            txtRpm = (TextView)itemView.findViewById(R.id.txtRpm );

            txtSpeed = (TextView)itemView.findViewById(R.id.txtSpeed);
            txtPrndl = (TextView)itemView.findViewById(R.id.txtPrndl);
            txtDt = (TextView)itemView.findViewById(R.id.txtDt);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mRecyclerViewOnClickListenerHack != null){
                mRecyclerViewOnClickListenerHack.onClickListener(v, getPosition());
            }
        }
    }

}
