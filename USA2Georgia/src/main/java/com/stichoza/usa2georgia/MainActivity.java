package com.stichoza.usa2georgia;

import java.util.List;
import java.util.Locale;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.stichoza.usa2georgia.data.FlightDataHolder;
import com.stichoza.usa2georgia.json.JSONParser;

public class MainActivity extends ActionBarActivity implements ActionBar.TabListener {

    /**
     * FragmentPagerAdapter will keep every loaded fragment in memory.
     * Replace with android.support.v4.app.FragmentStatePagerAdapter if memory fucks up
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;
    ListView flightsList;
    TextView flightsName;
    TextView flightsDate;
    TextView flightsText;

    protected List<FlightDataHolder> jsonData = new ArrayList<FlightDataHolder>();

    private static String API_URL = "http://stichoza.com/projects/usa2georgia/api/";

    private static final String FL_NAME = "name";
    private static final String FL_DATE = "date";
    private static final String FL_TEXT = "text";
    private static final String FL_ARRIVED = "arrived";

    JSONArray flights = null;
    JSONArray blog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        //mViewPager.setGravity(Gravity.RIGHT);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }

        /* End default shit */
        Log.e("x", "Yolo started");
        JSONParser jParser = new JSONParser();
        JSONArray json = jParser.getJSONFromUrl(API_URL);
        Log.e("x", "Yolo parser :v");

        try {
            JSONArray flightObj = json.getJSONArray(0);
            for (int i=0; i < flightObj.length(); i++) {
                JSONObject c = flightObj.getJSONObject(i);// Used JSON Object from Android

                // Storing each Json in a string variable
                String jrName = c.getString("name");
                String jrDate = c.getString("date");
                String jrText = c.getString("text");
                Boolean jrArrived = c.getBoolean("arrived");

                jsonData.add(new FlightDataHolder(jrName, jrDate, jrText, jrArrived));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            Log.e("x", "fuuuck :@");
        }

        ListView yourListView = (ListView) findViewById(R.id.list_flights);

        ListAdapter customAdapter = new ListAdapter(this, R.layout.main_results_flights, jsonData);

        yourListView.setAdapter(customAdapter);


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
        switch (item.getItemId()) {
            case R.id.action_about:
                return true;
            case R.id.action_refresh:
                //new JSONParse().execute();
                return true;
            case R.id.action_settings:
                //Intent settingsIntent = new Intent(this, SettingsActivity.class);
                //startActivity(settingsIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            //Toast.makeText(, "Selected tab: " + Integer.toString(getArguments()
            // .getInt(ARG_SECTION_NUMBER)), Toast.LENGTH_SHORT).show();
            View rootView = inflater.inflate(R.layout.fragment_main_flights, container, false);
            //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //textView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }


}
