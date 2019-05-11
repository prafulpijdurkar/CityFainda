package com.pp.backbase.cityfinder.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pp.backbase.cityfinder.R;
import com.pp.backbase.cityfinder.models.CityItemModel;
import com.pp.backbase.cityfinder.models.CoordinatesModel;

/**
 * Created by Praful Pijdurkar on 5/8/19.
 */
public class MapFragment extends SupportMapFragment {
    private final static String TAG = MapFragment.class.getSimpleName();
    public final static String FRAG_NAME_PORTRATE = MapFragment.class.getSimpleName();
    public final static String FRAG_NAME_LAND = MapFragment.class.getSimpleName() + "_LAND";

    public static MapFragment newInstance() {
        MapFragment fragment = new MapFragment();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        LinearLayout view = (LinearLayout) inflater.inflate(R.layout.fragment_map, container, false);

        View v = super.onCreateView(inflater, container, savedInstanceState);
        view.addView(v, 0);
        updateCoordinates(null);
        return view;

    }


     public void updateCoordinates(final CityItemModel cityItemModel) {
         this.getMapAsync(new OnMapReadyCallback() {
             public void onMapReady(GoogleMap googleMap) {
                 if (cityItemModel != null) {
                     CoordinatesModel coordinatesModel = cityItemModel.getCoordinatesModel();

                     if (coordinatesModel != null) {
                         googleMap.addMarker(new MarkerOptions().position(new LatLng(coordinatesModel.getLat(), coordinatesModel.getLon()))
                                 .title(cityItemModel.getName() + ", " + cityItemModel.getCountry()));
                         googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(coordinatesModel.getLat(), coordinatesModel.getLon())));
                     }
                 }
             }
         });
     }

}
