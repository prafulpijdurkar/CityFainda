package com.pp.backbase.cityfinder.presenters;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.pp.backbase.cityfinder.contract.About;
import com.pp.backbase.cityfinder.models.AboutInfo;
import com.pp.backbase.cityfinder.models.AboutModelImpl;
import com.pp.backbase.cityfinder.models.CityItemModel;

import java.lang.ref.WeakReference;

/**
 * Created by Backbase R&D B.V on 28/06/2018.
 */

public class AboutPresenterImpl implements About.Presenter {

    private final WeakReference<About.View> aboutView;
    private final AboutModelImpl aboutModel;

    public AboutPresenterImpl(About.View view, @NonNull Context context){
        this.aboutView = new WeakReference<>(view);
        this.aboutModel = new AboutModelImpl(this, context);
    }

    @Override
    public void getAboutInfo() {
        About.View aboutViewImpl = aboutView.get();

        aboutViewImpl.showProgress();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                aboutModel.getAboutInfo();
            }
        }, 500);
    }

    @Override
    public void onSuccess(AboutInfo aboutInfo) {
        About.View aboutViewImpl = aboutView.get();

        if(aboutViewImpl != null){
            aboutViewImpl.hideProgress();
            aboutViewImpl.setCompanyName(aboutInfo.getCompanyName());
            aboutViewImpl.setCompanyAddress(aboutInfo.getCompanyAddress());
            aboutViewImpl.setCompanyPostalCode(aboutInfo.getCompanyPostal());
            aboutViewImpl.setCompanyCity(aboutInfo.getCompanyCity());
            aboutViewImpl.setAboutInfo(aboutInfo.getAboutInfo());
        }

    }

    @Override
    public void onFail() {
        About.View aboutViewImpl = aboutView.get();
        if (aboutViewImpl != null){
            aboutViewImpl.hideProgress();
            aboutViewImpl.showError();
        }
    }

    public void updateValues(CityItemModel cityItemModel) {
        aboutView.get().setCompanyAddress(cityItemModel.getName() +", " + cityItemModel.getCountry()) ;
        aboutView.get().setCompanyCity(cityItemModel.getName());

    }
}
