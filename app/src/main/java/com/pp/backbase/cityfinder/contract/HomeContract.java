package com.pp.backbase.cityfinder.contract;

import com.pp.backbase.cityfinder.models.CityItemModel;

import java.util.List;

/**
 * Created by Praful Pijdurkar on 5/9/19.
 */
public interface HomeContract {
    interface Response {
        void getCityList();
        void onSuccess(List<CityItemModel> cityItemModelList);
        void onError(String errorMsg);
    }

    interface Presenter {
        void getCityList();
    }

    interface View {
        void setCityList(List<CityItemModel> cityItemModelList);
        void showError(String errorMsg);
        void showProgress();
        void hideProgress();
    }
}
