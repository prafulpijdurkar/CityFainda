package com.pp.backbase.cityfinder.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.pp.backbase.cityfinder.R;
import com.pp.backbase.cityfinder.contract.HomeContract;
import com.pp.backbase.cityfinder.models.CityItemModel;
import com.pp.backbase.cityfinder.presenters.HomePresenter;
import com.pp.backbase.cityfinder.ui.activities.MainActivity;
import com.pp.backbase.cityfinder.ui.adapters.CityAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Praful Pijdurkar on 5/7/19.
 */
public class CityFinderFragment extends Fragment implements HomeContract.View{

    EditText textBox;
    TextView text;
    ListView list;
    CityAdapter listAdapter;
    private ProgressBar progressBar;
    private android.view.View errorView;

    private static final String STATE_LIST = "State Adapter Data";
    ArrayList<CityItemModel> cityItemModelList;
    HomePresenter homePresenter = null;


    public CityFinderFragment() {
    }
    public static CityFinderFragment newInstance() {
        CityFinderFragment fragment = new CityFinderFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            cityItemModelList = savedInstanceState.getParcelableArrayList(STATE_LIST);
            listAdapter = new CityAdapter(this , cityItemModelList);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        homePresenter = new HomePresenter(this, (MainActivity) this.getActivity());
        setRetainInstance(true);

        initView(view);
        if(cityItemModelList == null && listAdapter == null ) {
            homePresenter.getCityList();
        } else {
            list.setAdapter(listAdapter);
            textBox.setVisibility(View.VISIBLE);
            list.setVisibility(View.VISIBLE);

        }

        return view;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void initView(View view ) {
        textBox = (EditText) view.findViewById(R.id.textBox);
        text = (TextView) view.findViewById(R.id.text);
        list = (ListView) view.findViewById(R.id.list);
        progressBar = view.findViewById(R.id.progressBar);
        errorView = view.findViewById(R.id.errorView);
        setTextChangeListner();
    }

    private void setTextChangeListner() {
        textBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().trim().length() >=0)
                    listAdapter.getFilter().filter(s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @Override
    public void setCityList(List<CityItemModel> cityItemModelList) {

        listAdapter = new CityAdapter(this , cityItemModelList);
        textBox.setVisibility(View.VISIBLE);
        list.setVisibility(View.VISIBLE);
        if (listAdapter != null) {
            list.setAdapter(listAdapter);
        }

    }

    @Override
    public void showError(String errorMsg) {
        Toast.makeText(getContext(), errorMsg,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(android.view.View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);


    }

    public HomePresenter getHomePresenter() {
        return homePresenter;
    }
}
