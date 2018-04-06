/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.sunshine;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * {@link ForecastAdapter} exposes a list of weather forecasts
 * from a {@link android.database.Cursor} to a {@link android.support.v7.widget.RecyclerView}.
 */
class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastAdapterViewHolder> {

    //  TODO done (14) Remove the mWeatherData declaration and the setWeatherData method
    //private String[] mWeatherData;
    //  TODO done (1) Declare a private final Context field called mContext
    private final Context mContext;

    /*
     * Below, we've defined an interface to handle clicks on items within this Adapter. In the
     * constructor of our ForecastAdapter, we receive an instance of a class that has implemented
     * said interface. We store that instance in this variable to call the onClick method whenever
     * an item is clicked in the list.
     */
    final private ForecastAdapterOnClickHandler mClickHandler;

    /**
     * The interface that receives onClick messages.
     */
    public interface ForecastAdapterOnClickHandler {
        void onClick(String weatherForDay);
    }

//  TODO done (2) Declare a private Cursor field called mCursor
    private Cursor mCursor;
//  TODO done (3) Add a Context field to the constructor and store that context in mContext

    /**
     * Creates a ForecastAdapter.
     *
     * @param clickHandler The on-click handler for this adapter. This single handler is called
     *                     when an item is clicked.
     */
    public ForecastAdapter(ForecastAdapterOnClickHandler clickHandler, Context context) {
        mClickHandler = clickHandler;
        mContext = context;
    }

    /**
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     *
     * @param viewGroup The ViewGroup that these ViewHolders are contained within.
     * @param viewType  If your RecyclerView has more than one type of item (which ours doesn't) you
     *                  can use this viewType integer to provide a different layout. See
     *                  {@link android.support.v7.widget.RecyclerView.Adapter#getItemViewType(int)}
     *                  for more details.
     * @return A new ForecastAdapterViewHolder that holds the View for each list item
     */
    @Override
    public ForecastAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.forecast_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new ForecastAdapterViewHolder(view);
    }

    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position. In this method, we update the contents of the ViewHolder to display the weather
     * details for this particular position, using the "position" argument that is conveniently
     * passed into us.
     *
     * @param forecastAdapterViewHolder The ViewHolder which should be updated to represent the
     *                                  contents of the item at the given position in the data set.
     * @param position                  The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(ForecastAdapterViewHolder forecastAdapterViewHolder, int position) {
//      TODO done (5) Delete the current body of onBindViewHolder
//      TODO done (6) Move the cursor to the appropriate position
        mCursor.moveToPosition(position);
//      TODO done (7) Generate a weather summary with the date, description, high and low
        //String weatherForThisDay = mWeatherData[position];
        double highInCelsius = mCursor.getDouble(MainActivity.INDEX_WEATHER_MAX_TEMP);
        String weatherDesc = mCursor.getString(MainActivity.INDEX_WEATHER_DESCRIPTION);
        double high = mCursor.getDouble(MainActivity.INDEX_WEATHER_HIGH_TEMP);
        double low = mCursor.getDouble(MainActivity.INDEX_WEATHER_LOW_TEMP);
        String dateString = mCursor.getString(MainActivity.INDEX_WEATHER_DATE).toString();
        String description = mCursor.getString(MainActivity.INDEX_WEATHER_DESCRIPTION);

        String weatherForThisDay = mWeatherData[position];
//      TODO done (8) Display the summary that you created above
        String weatherSummary = dateString + " - " + description + " - " + high + " - " + low;
        //forecastAdapterViewHolder.weatherSummary.setText(weatherForThisDay);
    }

    /**
     * This method simply returns the number of items to display. It is used behind the scenes
     * to help layout our Views and for animations.
     *
     * @return The number of items available in our forecast
     */
    @Override
    public int getItemCount() {
//      TODO done (9) Delete the current body of getItemCount
        //if (null == mWeatherData) return 0;
        //return mWeatherData.length;
//      TODO done (10) If mCursor is null, return 0. Otherwise, return the count of mCursor
        if (mCursor == null) return 0;
        else return mCursor.getCount();

    }

    /**
     * This method is used to set the weather forecast on a ForecastAdapter if we've already
     * created one. This is handy when we get new data from the web but don't want to create a
     * new ForecastAdapter to display it.
     *
     * @param weatherData The new weather data to be displayed.
     */
    /*public void setWeatherData(String[] weatherData) {
        mWeatherData = weatherData;
        notifyDataSetChanged();
    }*/

//  TODO done (11) Create a new method that allows you to swap Cursors.
    protected void swapCursors (Cursor c1, Cursor c2){
        Cursor tmp = c1;
        c1 = c2;
        c2= tmp;
        //      TODO done (12) After the new Cursor is set, call notifyDataSetChanged
        this.notifyDataSetChanged();
    }



    /**
     * A ViewHolder is a required part of the pattern for RecyclerViews. It mostly behaves as
     * a cache of the child views for a forecast item. It's also a convenient place to set an
     * OnClickListener, since it has access to the adapter and the views.
     */
    class ForecastAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView weatherSummary;

        ForecastAdapterViewHolder(View view) {
            super(view);
            weatherSummary = (TextView) view.findViewById(R.id.tv_weather_data);
            view.setOnClickListener(this);
        }

        /**
         * This gets called by the child views during a click.
         *
         * @param v The View that was clicked
         */
        @Override
        public void onClick(View v) {
            //  TODO (13) Instead of passing the String from the data array, use the weatherSummary text!
            int adapterPosition = getAdapterPosition();
            //String weatherForDay = mWeatherData[adapterPosition];
            //mClickHandler.onClick(weatherForDay);
            mClickHandler.onClick(weatherSummary.getText().toString());
        }
    }
}