package com.pp.backbase.cityfinder.presenters;

import android.content.res.Configuration;
import android.os.Handler;

import com.pp.backbase.cityfinder.contract.HomeContract;
import com.pp.backbase.cityfinder.models.CityItemModel;
import com.pp.backbase.cityfinder.netwok.MockResponseManager;
import com.pp.backbase.cityfinder.ui.activities.BaseActivity;
import com.pp.backbase.cityfinder.ui.activities.MainActivity;
import com.pp.backbase.cityfinder.ui.fragment.AboutFragment;
import com.pp.backbase.cityfinder.ui.fragment.MapFragment;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Praful Pijdurkar on 5/9/19.
 * Presenter of CityFinder HOme screen.
 */
public class HomePresenter implements HomeContract.Presenter,HomeContract.Response {
    private final WeakReference<HomeContract.View> homeView;
    List<CityItemModel> cityItemModelList = null;
    MainActivity activity;

    public HomePresenter(HomeContract.View view, MainActivity activity){
        this.homeView = new WeakReference<>(view);
        this.activity = activity;
     }


    @Override
    public void getCityList() {
      homeView.get().showProgress();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MockResponseManager.getInstance().getCityList(HomePresenter.this);
            }
        }, 100);
     }

    @Override
    public void onSuccess(List<CityItemModel> cityItemModelList) {
        if(homeView.get() != null) {
            homeView.get().hideProgress();
        }

        if(cityItemModelList != null && homeView.get() != null) {
            this.cityItemModelList = cityItemModelList;
            homeView.get().setCityList(cityItemModelList);
        }
    }

    @Override
    public void onError(String errorMsg) {
        if(homeView.get() != null) {
            homeView.get().hideProgress();
            homeView.get().showError(errorMsg);
        }
    }


    public void showMapFragment(final CityItemModel cityItemModel) {
        MapFragment mapFragment = (MapFragment)activity. getFragmentView(BaseActivity.FRAGMENT_NAME_MAP);
        int orientation = activity.getResources().getConfiguration().orientation;
        String strTag = (orientation == Configuration.ORIENTATION_PORTRAIT) ? MapFragment.FRAG_NAME_PORTRATE : MapFragment.FRAG_NAME_LAND;
        boolean isLandscape = (orientation == Configuration.ORIENTATION_LANDSCAPE) ? true : false;
        activity.addFragment(mapFragment, !isLandscape, strTag, isLandscape);

        mapFragment.updateCoordinates(cityItemModel);

    }

    public void getShowAbout(final CityItemModel cityItemModel) {
        final AboutFragment aboutFragment = (AboutFragment) activity.getFragmentView(BaseActivity.FRAGMENT_NAME_ABOUT);
        int orientation =activity.getResources().getConfiguration().orientation;
        String strTag = (orientation == Configuration.ORIENTATION_PORTRAIT) ? MapFragment.FRAG_NAME_PORTRATE : MapFragment.FRAG_NAME_LAND;
        boolean isLandscape = (orientation == Configuration.ORIENTATION_LANDSCAPE) ? true : false;
        activity.addFragment(aboutFragment, !isLandscape, strTag, isLandscape);

        new Handler().postDelayed(new Runnable() {
                                       public void run() {
                                           aboutFragment.getAboutPresenter().updateValues(cityItemModel);

                                       }
                                  },600);

    }
}
