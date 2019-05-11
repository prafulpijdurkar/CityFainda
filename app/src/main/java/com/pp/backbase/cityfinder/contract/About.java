package com.pp.backbase.cityfinder.contract;

import com.pp.backbase.cityfinder.models.AboutInfo;

/**
 * Created by Backbase R&D B.V on 28/06/2018.
 * MVP contract for AboutFragment
 */

public interface About {

    interface Model {
        void getAboutInfo();
    }

    interface Presenter {
        void getAboutInfo();
        void onSuccess(AboutInfo aboutInfo);
        void onFail();
    }

    interface View {
        void setCompanyName(String companyName);
        void setCompanyAddress(String companyAddress);
        void setCompanyPostalCode(String postalCode);
        void setCompanyCity(String companyCity);
        void setAboutInfo(String info);
        void showError();
        void showProgress();
        void hideProgress();
    }
}
