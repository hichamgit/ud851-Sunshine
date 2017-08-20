package com.example.android.sunshine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.zip.Inflater;


/**
 * Created by hicham on 8/17/17.
 */
// Within ForecastAdapter.java /////////////////////////////////////////////////////////////////
// done (15) Add a class file called ForecastAdapter
// done (22) Extend RecyclerView.Adapter<ForecastAdapter.ForecastAdapterViewHolder>
// done (23) Create a private string array called mWeatherData
// done (47) Create the default constructor (we will pass in parameters in a later lesson)
// done (16) Create a class within ForecastAdapter called ForecastAdapterViewHolder
// done (17) Extend RecyclerView.ViewHolder


public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastAdapterViewHolder> {

    private String[] mWeatherData;

    public ForecastAdapter() {

    }

    class ForecastAdapterViewHolder extends RecyclerView.ViewHolder{
        // Within ForecastAdapterViewHolder ///////////////////////////////////////////////////////////
        // done (18) Create a public final TextView variable called mWeatherTextView
        // done (19) Create a constructor for this class that accepts a View as a parameter
        // done (20) Call super(view) within the constructor for ForecastAdapterViewHolder
        // done (21) Using view.findViewById, get a reference to this layout's TextView and save it to mWeatherTextView

        public final TextView mWeatherTextView;

        public ForecastAdapterViewHolder(View itemView) {
            super(itemView);
            mWeatherTextView  = (TextView) itemView.findViewById(R.id.tv_weather_data);
        }


    }

    @Override
    public ForecastAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean attchedImmediately = false;
        View view = inflater.inflate(R.layout.forecast_list_item,parent, attchedImmediately);
        return new ForecastAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ForecastAdapterViewHolder holder, int position) {
        holder.mWeatherTextView.setText(mWeatherData[position]);
    }

    @Override
    public int getItemCount() {
        return (mWeatherData ==null ) ? 0 : mWeatherData.length;
        //Return 0 if mWeatherData is null, or the size of mWeatherData if it is not null
    }
    // setWeatherData method saves the weatherData to mWeatherData
    public void setWeatherData(String[] weatherData){
        this.mWeatherData = weatherData;
        notifyDataSetChanged();
    }
}
