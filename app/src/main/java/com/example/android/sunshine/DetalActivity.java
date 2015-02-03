package com.example.android.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Set;


public class DetalActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detal);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detal, menu);
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
            Intent intent=new Intent(this,SettngsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private ImageView mIconView;
        private TextView mFriendlyDateView;
        private TextView mDateView;
        private TextView mDescriptionView;
        private TextView mHighTempView;
        private TextView mLowTempView;
        private TextView mHumidityView;
        private TextView mWindView;
        private TextView mPressureView;

        public PlaceholderFragment() {
            setHasOptionsMenu(true);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_detal, container, false);


            Intent intent = getActivity().getIntent();
            String forecast = intent.getStringExtra(Intent.EXTRA_TEXT);
           // TextView hello=(TextView)rootView.findViewById(R.id.hello_view);
            String[] parts = forecast.split("-");

            //hello.setText(forecast);
            mIconView = (ImageView) rootView.findViewById(R.id.detail_icon);
            //String cloud="Clouds";
            HashMap<String,Integer> picMap = new HashMap<>();
            picMap.put("Clouds",R.drawable.art_clouds);
            picMap.put("Rain",R.drawable.art_rain);
            picMap.put("Fog",R.drawable.art_fog);
            picMap.put("Snow",R.drawable.art_snow);
            picMap.put("Clear",R.drawable.art_clear);
            picMap.put("Storm",R.drawable.art_storm);
            Set<String> keys = picMap.keySet();

            String key=parts[1].toString().trim();
            mIconView.setImageDrawable(getResources().getDrawable(picMap.get(key)));

            mDateView = (TextView) rootView.findViewById(R.id.detail_date_textview);
            mDateView.setText(parts[0]);

            mFriendlyDateView = (TextView) rootView.findViewById(R.id.detail_day_textview);
            //mFriendlyDateView.setText(parts[0]);
            mDescriptionView = (TextView) rootView.findViewById(R.id.detail_forecast_textview);
            mDescriptionView.setText(parts[1]);
            mHighTempView = (TextView) rootView.findViewById(R.id.detail_high_textview);
            mHighTempView.setText("Max:"+parts[2]);
            mLowTempView = (TextView) rootView.findViewById(R.id.detail_low_textview);
            mLowTempView.setText("Min:"+parts[3]);
            mHumidityView = (TextView) rootView.findViewById(R.id.detail_humidity_textview);
            mHumidityView.setText("Humidity:"+parts[4]);
            mWindView = (TextView) rootView.findViewById(R.id.detail_wind_textview);
            mPressureView = (TextView) rootView.findViewById(R.id.detail_pressure_textview);
            mPressureView.setText("Pressure:"+parts[5]);
            return rootView;
            
        }
    }
}
