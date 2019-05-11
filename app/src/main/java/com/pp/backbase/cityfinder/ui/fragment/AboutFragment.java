package com.pp.backbase.cityfinder.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pp.backbase.cityfinder.R;
import com.pp.backbase.cityfinder.presenters.AboutPresenterImpl;
import com.pp.backbase.cityfinder.base.MainApplication;
import com.pp.backbase.cityfinder.contract.About;


public class AboutFragment extends Fragment implements About.View {


    public final static String FRAG_NAME_PORTRATE = AboutFragment.class.getSimpleName();
    public final static String FRAG_NAME_LAND = AboutFragment.class.getSimpleName() + "_LAND";
    private TextView companyName;
    private TextView companyAddress;
    private TextView companyPostal;
    private TextView companyCity;
    private TextView aboutInfo;
    private ProgressBar progressBar;
    private android.view.View errorView;
    private android.view.View infoContainer;
    AboutPresenterImpl aboutPresenter;

    public static AboutFragment newInstance() {
        AboutFragment fragment = new AboutFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);

          aboutPresenter = new AboutPresenterImpl(this, MainApplication.applicationContext);
        companyName = view.findViewById(R.id.companyName);
        companyAddress = view.findViewById(R.id.companyAdress);
        companyPostal = view.findViewById(R.id.companypostal);
        companyCity = view.findViewById(R.id.companyCity);
        aboutInfo = view.findViewById(R.id.aboutInfo);
        progressBar = view.findViewById(R.id.progressBar);
        errorView = view.findViewById(R.id.errorView);
        infoContainer = view.findViewById(R.id.infoContainer);
        aboutPresenter.getAboutInfo();

        return view;// super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void setCompanyName(String companyNameString) {
        infoContainer.setVisibility(android.view.View.VISIBLE);
        companyName.setText(companyNameString);
    }

    @Override
    public void setCompanyAddress(String companyAddressString) {
        companyAddress.setText(companyAddressString);
    }

    @Override
    public void setCompanyPostalCode(String postalCodeString) {
        companyPostal.setText(postalCodeString);
    }

    @Override
    public void setCompanyCity(String companyCityString) {
        companyCity.setText(companyCityString);
    }

    @Override
    public void setAboutInfo(String infoString) {
        aboutInfo.setText(infoString);
    }

    @Override
    public void showError() {
        errorView.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(android.view.View.GONE);
    }

    public AboutPresenterImpl getAboutPresenter() {
        return aboutPresenter;
    }
}
