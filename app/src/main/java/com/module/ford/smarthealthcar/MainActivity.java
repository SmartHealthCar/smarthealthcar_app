package com.module.ford.smarthealthcar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.module.ford.smarthealthcar.DAO.GetDataFromServer;
import com.module.ford.smarthealthcar.DAO.SendDataToServer;
import com.module.ford.smarthealthcar.model.Car;
import com.module.ford.smarthealthcar.model.CarData;
import com.module.ford.smarthealthcar.model.JsonArrayConverter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

//    private ClientAdapter adapter;
//    private RecyclerView recyclerView;
//    private ProgressBar progressBar;
    private AppBarLayout appBarLayout;
    private FloatingActionButton floatingButton;
    private Button buttonStopCycle;
    private GraphView graph;

    private List<CarData> mListCarData = new ArrayList<>();

    private TextView rmpTextView;
    private TextView distanceTextView;
    private TextView fuelConsumptionTextView;

    private Button fuelButton;
    private Button rpmButton;
    private Button distanceButton;



    @SuppressLint("LongLogTag")
    @Override
    protected void onResume() {
        super.onResume();

        rmpTextView = (TextView) findViewById(R.id.rpm_text_view);
        distanceTextView = (TextView) findViewById(R.id.distance_text_view);
        fuelConsumptionTextView = (TextView) findViewById(R.id.fuel_consumption_text_view);

        fuelButton = (Button) findViewById(R.id.button_fuel);
        rpmButton = (Button) findViewById(R.id.button_rpm);
        distanceButton = (Button) findViewById(R.id.button_distance);

        mListCarData = Shared.getInstance().getListCarData() == null ? new ArrayList<CarData>() : Shared.getInstance().getListCarData();

        Car car = new Car();

        if(mListCarData.size() > 0){
            int lastItemList = mListCarData.size() -1;


            Double distance_traveled = 0.0;

            try {
                distance_traveled = Double.parseDouble(mListCarData.get(lastItemList).getOdometer())- Double.parseDouble(mListCarData.get(0).getOdometer());
            }catch (Exception e){
                distance_traveled = 0.0;
            }


            Double average_fuel_consumption = new Double(0.0);

            try {
                average_fuel_consumption = Double.parseDouble(mListCarData.get(0).getFuelLevel()) - Double.parseDouble(mListCarData.get(lastItemList).getFuelLevel());
                average_fuel_consumption /= distance_traveled;
            }catch (Exception e){
                average_fuel_consumption = 0.0;
            }


            int average_rpm = 0;

            for (int i=0 ; i<mListCarData.size(); i++ ){
                average_rpm += Integer.valueOf(mListCarData.get(i).getRpm());
            }
            average_rpm /= lastItemList;

            car = new Car(  average_fuel_consumption,
                    distance_traveled,
                    String.valueOf(average_rpm),
                    mListCarData.get(0).getVin(),
                    mListCarData.get(lastItemList).getOdometer());



            String serverResponse = "404";

            try {
                serverResponse = new SendDataToServer("https://smarthealthcarapi.herokuapp.com/car_informations")
                        .execute(getCarClassParams(car))
                        .get();
            } catch ( ExecutionException | InterruptedException | JSONException e) {
                e.printStackTrace();
            }

            Log.d("RESPONSE_CAR_INFORMATIONS", serverResponse);

        }



        String serverResponse = "404";
        JSONObject jsonObject=null;

        String vin = "null";

        if(mListCarData.size() != 0){
            vin = mListCarData.get(0).getVin();
        }

        try {
            jsonObject = new JSONObject();
            jsonObject.put("vin", vin);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            serverResponse = new SendDataToServer("https://smarthealthcarapi.herokuapp.com/get_all_data")
                                .execute(jsonObject.toString())
                                .get();
        } catch ( ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        Log.d("RESPOSTE ON RESUME", serverResponse);

        JSONObject object = null;
        JSONArray classArrayJson = null;

        ArrayList<Car> cars = null;

        try {
            object = new JSONObject(serverResponse);

            classArrayJson = object.getJSONArray("all_data");

            cars = getArrayListCarInformation(classArrayJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("OBJECT CARS", String.valueOf(cars.size()));


        BarGraphSeries<DataPoint> series = new BarGraphSeries<>();


        for (int i = 0; i < cars.size(); i++) {
            //Graph
            DataPoint point = new DataPoint(i, cars.get(i).getAverage_fuel_consumption());
            series.appendData(point, true, cars.size());
        }

        series.setSpacing(50);

        // draw values on top
        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.RED);
        //series.setValuesOnTopSize(50);

        graph.addSeries(series);

        final ArrayList<Car> finalCars = cars;
        fuelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                BarGraphSeries<DataPoint> series = new BarGraphSeries<>();
                for (int i = 0; i < finalCars.size(); i++) {
                    //Graph
                    DataPoint point = new DataPoint(i, finalCars.get(i).getAverage_fuel_consumption());
                    series.appendData(point, true, finalCars.size());
                }

                series.setSpacing(50);

                // draw values on top
                series.setDrawValuesOnTop(true);
                series.setValuesOnTopColor(Color.RED);
                //series.setValuesOnTopSize(50);
                graph.getViewport().setMaxY(30);
                graph.removeAllSeries();
                graph.addSeries(series);
            }
        });

        rpmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BarGraphSeries<DataPoint> series = new BarGraphSeries<>();
                for (int i = 0; i < finalCars.size(); i++) {
                    //Graph
                    DataPoint point = new DataPoint(i, Double.parseDouble(finalCars.get(i).getAverage_rpm()));
                    series.appendData(point, true, finalCars.size());
                }

                series.setSpacing(50);

                // draw values on top
                series.setDrawValuesOnTop(true);
                series.setValuesOnTopColor(Color.RED);
                //series.setValuesOnTopSize(50);

                graph.getViewport().setMaxY(8000);

                graph.removeAllSeries();
                graph.addSeries(series);

            }
        });

        distanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BarGraphSeries<DataPoint> series = new BarGraphSeries<>();

                for (int i = 0; i < finalCars.size(); i++) {
                    //Graph
                    DataPoint point = new DataPoint(i, finalCars.get(i).getDistance_traveled());
                    series.appendData(point, true, finalCars.size());
                }

                series.setSpacing(50);

                // draw values on top
                series.setDrawValuesOnTop(true);
                series.setValuesOnTopColor(Color.RED);
                //series.setValuesOnTopSize(50);

                graph.getViewport().setMaxY(302);
                graph.removeAllSeries();
                graph.addSeries(series);
            }
        });



        distanceTextView.setText(cars.get(cars.size()-1).getDistance_traveled().toString());
        rmpTextView.setText(cars.get(cars.size()-1).getAverage_rpm());
        fuelConsumptionTextView.setText(cars.get(cars.size()-1).getAverage_fuel_consumption().toString());

    }

    private ArrayList<Car> getArrayListCarInformation(JSONArray array) throws JSONException {

        ArrayList<Car> cars = new ArrayList<>();
        for(int i = 0; i < array.length(); i++){
            Car car = getUserClassFromJson(array.getJSONObject(i));
            cars.add(car);
        }

        return cars;
    }

    private Car getUserClassFromJson(JSONObject jsonObject) throws JSONException {
        Car car = new Car();

        car.setAverage_fuel_consumption(Double.valueOf(jsonObject.getString("average_fuel_consumption")));
        car.setDistance_traveled(Double.valueOf(jsonObject.getString("distance_traveled")));
        car.setAverage_rpm(jsonObject.getString("average_rpm"));

        return car;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        buttonStopCycle = findViewById(R.id.stop_cycle_button);
        buttonStopCycle.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onClick(View v) {

                int lastItemList = mListCarData.size() -1;


                Double distance_traveled =  Double.parseDouble(mListCarData.get(lastItemList).getOdometer())- Double.parseDouble(mListCarData.get(0).getOdometer());
                Double average_fuel_consumption = Double.parseDouble(mListCarData.get(0).getFuelLevel()) - Double.parseDouble(mListCarData.get(lastItemList).getFuelLevel());
                average_fuel_consumption /= distance_traveled;

                int average_rpm = 0;

                for (int i=0 ; i<mListCarData.size(); i++ ){
                    average_rpm += Integer.valueOf(mListCarData.get(i).getRpm());
                }
                average_rpm /= lastItemList;

                Car car = new Car(  average_fuel_consumption,
                                    distance_traveled,
                                    String.valueOf(average_rpm),
                                    mListCarData.get(0).getVin(),
                                    mListCarData.get(lastItemList).getOdometer());



                String serverResponse = "404";

                try {
                    serverResponse = new SendDataToServer("https://smarthealthcarapi.herokuapp.com/car_informations")
                                        .execute(getCarClassParams(car))
                                        .get();
                } catch ( ExecutionException | InterruptedException | JSONException e) {
                    e.printStackTrace();
                }

                Log.d("RESPONSE_CAR_INFORMATIONS", serverResponse);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        initFloatingButton();
        initAppBarLayout();

        setTitle("");

        graph = (GraphView) findViewById(R.id.graph);

        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[] {
        });

//       // set manual X bounds
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(30);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(10);

        // enable scaling and scrolling
        graph.getViewport().setScalable(true);
        graph.getViewport().setScalableY(true);


        graph.addSeries(series);

        // styling
        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX()*255/4, (int) Math.abs(data.getY()*255/6), 100);
            }
        });

        series.setSpacing(50);

        // draw values on top
        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.RED);
        //series.setValuesOnTopSize(50);



        //GET
//        String resposta = "";
//        try {
//            resposta = new GetDataFromServer().execute("https://smarthealthcarapi.herokuapp.com/get_all_data/123").get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//
//        Log.d("RESPOSTA", resposta);

        //POST
//        Car car = new Car("2018-02-01 16:16:04 -0200", "2018-02-01 16:16:59 -0200",
//                21.0, 100.2,
//                "1450", "2313", "50000");
//
//        String serverResponse = "404";
//
//        try {
//            serverResponse = new SendDataToServer().execute(getCarClassParams(car)).get();
//        } catch (JSONException | ExecutionException | InterruptedException e) {
//            e.printStackTrace();
//        }

    }

    public String getCarClassParams(Car car) throws JSONException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("average_fuel_consumption", car.getAverage_fuel_consumption().toString());
        jsonObject.put("distance_traveled", car.getDistance_traveled().toString());
        jsonObject.put("average_rpm", car.getAverage_rpm());
        jsonObject.put("vin", car.getVin());
        jsonObject.put("odometer", car.getOdometer());

        return jsonObject.toString();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if(id == R.id.erase_data){
            Shared.getInstance().ListCarData = new ArrayList<>();
            Toast.makeText(getApplicationContext(), "Dados Coletados Apagados", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_average_consumption) {
            //startActivity(new  Intent(getApplicationContext(), ));
        } else if (id == R.id.nav_time_traveled) {

        }else if(id == R.id.nav_average_rpm){

        }else if(id == R.id.nav_duration){

        }else if(id ==R.id.nav_manage){

        }else if (id == R.id.nav_share) {
            actionShare();
        } else if(id == R.id.nav_avaliate) {
            actionOpenGooglePLay();
        }else if (id == R.id.nav_info) {
            //startActivity(new  Intent(getApplicationContext(), ));
        } else if (id == R.id.nav_exit) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initFloatingButton(){
        floatingButton = (FloatingActionButton) findViewById(R.id.fab);

        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext() , DetailInfoAct.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(i);
            }
        });
    }

    private void initAppBarLayout(){
        appBarLayout = (AppBarLayout) findViewById(R.id.app_bar_layout);
    }

    public void actionOpenGooglePLay(){

        final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }

    }

    public void actionShare(){
        try {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, "RedCup");
            String sAux = "\nDeixa eu te recomendar este aplicatico\n\n";
            sAux = sAux + "https://play.google.com/store/apps/details?id=com.arthur.redcup \n\n";
            i.putExtra(Intent.EXTRA_TEXT, sAux);
            startActivity(Intent.createChooser(i, "choose one"));
        } catch(Exception e) {

        }
    }


}
//
//class SendDataToServer extends AsyncTask<String,String,String> {
//
//    protected String doInBackground(String... params) {
//        String JsonResponse = null;
//        String JsonDATA = params[0];
//        HttpURLConnection urlConnection = null;
//        BufferedReader reader = null;
//        try {
//            URL url = new URL(" https://smarthealthcarapi.herokuapp.com/car_informations");
//            urlConnection = (HttpURLConnection) url.openConnection();
//            urlConnection.setDoOutput(true);
//            // is output buffer writter
//            urlConnection.setRequestMethod("POST");
//            urlConnection.setRequestProperty("Content-Type", "application/json");
//            urlConnection.setRequestProperty("Accept", "application/json");
////set headers and method
//            Writer writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8"));
//            writer.write(JsonDATA);
//// json data
//            writer.close();
//            InputStream inputStream = urlConnection.getInputStream();
////input stream
//            StringBuffer buffer = new StringBuffer();
//            if (inputStream == null) {
//                // Nothing to do.
//                return null;
//            }
//            reader = new BufferedReader(new InputStreamReader(inputStream));
//
//            String inputLine;
//            while ((inputLine = reader.readLine()) != null)
//                buffer.append(inputLine + "\n");
//            if (buffer.length() == 0) {
//                // Stream was empty. No point in parsing.
//                return null;
//            }
//            JsonResponse = buffer.toString();
////response data
//            Log.i("TAG",JsonResponse);
//            //send to post execute
//            return JsonResponse;
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (urlConnection != null) {
//                urlConnection.disconnect();
//            }
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (final IOException e) {
//                    Log.e("TAG", "Error closing stream", e);
//                }
//            }
//        }
//        return null;
//    }
//
//    @Override
//    protected void onPostExecute(String s) {
//    }
//
//}
//
