package com.pp.backbase.cityfinder.netwok;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pp.backbase.cityfinder.contract.About;
import com.pp.backbase.cityfinder.models.AboutInfo;
import com.pp.backbase.cityfinder.base.MainApplication;
import com.pp.backbase.cityfinder.contract.HomeContract;
import com.pp.backbase.cityfinder.models.CityItemModel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

/**
 * Created by Praful Pijdurkar on 5/7/19.
 *This class is a Singlton Object class, This is replicate the response from the server.
 */

public class MockResponseManager  {
    public static final String REQUEST_CITY_LIST= "REQUEST_CITY_LIST";
    public static final String REQUEST_ABOUT= "REQUEST_ABOUT";
    public static final String ERROR_MSG= "Unable to get city List. Please try again later.";
    private static final int BUFFER_SIZE = 1024 * 4;
    private static final String TAG = MockResponseManager.class.getSimpleName();


    public static MockResponseManager mockResponseManager;

    private MockResponseManager() {
    }


    public static MockResponseManager getInstance() {
        if(mockResponseManager == null) {
            mockResponseManager = new MockResponseManager();
        }
        return mockResponseManager;
    }

  // Method used to read response from raw folder.
    public static String readFakeResponseFromAssets(int resourceId) throws IOException {

        InputStream stream = MainApplication.applicationContext.getResources().openRawResource(resourceId);


             try{
                 Log.e(TAG, "Got Input Stream:" + resourceId);

                 byte[] formArray = new byte[stream.available()];
                 Log.e(TAG, "formArray:" + formArray.length);

                 stream.read(formArray);
                 stream.close();
                return new String(formArray);
            }catch (IOException ex){
                Log.e(TAG, ex.getLocalizedMessage(), ex);
            }

        return null;
    }

    // Get the city list.
    public void getCityList(HomeContract.Response presenter)   {
        try {

            Log.e(TAG, "Getting City List");

            String data = readFakeResponseFromAssets(getResourceId(REQUEST_CITY_LIST));
            Log.e(TAG, "Data" + data);

            ObjectMapper mapper = new ObjectMapper();
            List<CityItemModel> cityItemModelList = mapper.readValue(data, mapper.getTypeFactory().constructCollectionType(List.class, CityItemModel.class));                               // read from json string
            Log.e(TAG, "cityItemModelList:" + cityItemModelList.size());
            Collections.sort(cityItemModelList);
            presenter.onSuccess(cityItemModelList);
         }catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "Problem while reading data from file");
            presenter.onError(ERROR_MSG);

        }

    }

    // To get the About info.
    public void getAbout(About.Presenter presenter)   {
        try {

            Log.e(TAG, "Getting City List");

            String data = readFakeResponseFromAssets(getResourceId(REQUEST_ABOUT));
            Log.e(TAG, "Data" + data);

            ObjectMapper mapper = new ObjectMapper();
            AboutInfo aboutInfo = mapper.readValue(data,  AboutInfo.class);                               // read from json string
            Log.e(TAG, "cityItemModelList:" + aboutInfo.getAboutInfo());

            presenter.onSuccess(aboutInfo);
        }catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "Problem while reading data from file");
            presenter.onFail();

        }

    }

    private int getResourceId(String filename) {
        Log.i(TAG, "Finding Resouce Id:"  );

        String fileNameJSON = "response_cities";
        switch (filename) {
          case  REQUEST_CITY_LIST :
              fileNameJSON = "response_cities";
              break;
            case  REQUEST_ABOUT :
                fileNameJSON = "about_info";
                break;
     }
        Log.i(TAG, "Finding Resouce Id1:"  );

        int identifier = MainApplication.applicationContext.getResources().getIdentifier(fileNameJSON, "raw", MainApplication.applicationContext.getPackageName());
        Log.i(TAG, "Got Resouce Id:" + identifier);


        return identifier;
    }
}
