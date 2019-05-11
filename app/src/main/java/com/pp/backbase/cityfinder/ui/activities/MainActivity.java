package com.pp.backbase.cityfinder.ui.activities;

import android.content.res.Configuration;
import android.os.Bundle;

import com.pp.backbase.cityfinder.R;
import com.pp.backbase.cityfinder.ui.fragment.AboutFragment;
import com.pp.backbase.cityfinder.models.CityItemModel;
import com.pp.backbase.cityfinder.ui.fragment.CityFinderFragment;
import com.pp.backbase.cityfinder.ui.fragment.MapFragment;

/**
 * Created by Praful Pijdurkar on 5/7/19.
 */

public class MainActivity extends BaseActivity {

    MapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            mapFragment = MapFragment.newInstance();
            addFragment(CityFinderFragment.newInstance(), false, CityFinderFragment.class.getSimpleName(), false);
        }
        addMapToContainer1();

    }


    protected void addMapToContainer1() {
        if (this.getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE) {
            if (mapFragment == null)
                mapFragment = MapFragment.newInstance();

            addFragment(mapFragment, false, MapFragment.FRAG_NAME_LAND, true);
        }
    }


}
