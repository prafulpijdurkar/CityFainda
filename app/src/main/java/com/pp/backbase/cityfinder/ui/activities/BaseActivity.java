package com.pp.backbase.cityfinder.ui.activities;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.pp.backbase.cityfinder.R;
import com.pp.backbase.cityfinder.ui.fragment.AboutFragment;
import com.pp.backbase.cityfinder.ui.fragment.MapFragment;

/**
 * Created by Praful Pijdurkar on 5/10/19.
 */
public class BaseActivity extends AppCompatActivity {

    public static final String FRAGMENT_NAME_MAP = "MapFragment";
    public static final String FRAGMENT_NAME_ABOUT = "AboutFragment";


    public Fragment getFragmentView(String fragmentName) {
        int orientation = this.getResources().getConfiguration().orientation;
        Fragment fragmentAdded = null;

        switch (fragmentName) {
            case FRAGMENT_NAME_MAP:
                String strTag = (orientation == Configuration.ORIENTATION_PORTRAIT) ? MapFragment.FRAG_NAME_PORTRATE : MapFragment.FRAG_NAME_LAND;
                fragmentAdded = getFragmentByName(strTag);
                if(!(fragmentAdded instanceof MapFragment)) {
                    fragmentAdded = null;
                }
                if (fragmentAdded == null) {
                    fragmentAdded = MapFragment.newInstance();
                }
                break;
            case FRAGMENT_NAME_ABOUT:
                strTag = (orientation == Configuration.ORIENTATION_PORTRAIT) ? AboutFragment.FRAG_NAME_PORTRATE : AboutFragment.FRAG_NAME_LAND;
                fragmentAdded = getFragmentByName(strTag);
                if (fragmentAdded == null) {
                    fragmentAdded = AboutFragment.newInstance();
                }
                break;
        }
        return fragmentAdded;

    }

    private Fragment getFragmentByName(String tag) {
        return getSupportFragmentManager().findFragmentByTag(tag);

    }

    public void addFragment(Fragment fragment, boolean addtoBackstack, String tag, boolean landscape) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (landscape) {
            if(!fragment.isAdded())
                transaction.add(R.id.container1, fragment, tag);


        } else {
            if (getSupportFragmentManager().findFragmentByTag(tag) != null) {
                transaction.remove(fragment);     //stacco il frammento dal container A
                transaction.commit();
            }
            transaction.add(R.id.container, fragment, tag);

        }

        if (addtoBackstack) {
            transaction.addToBackStack(fragment.toString());
        }
        transaction.commit();

        Log.i("MainActivity", "End of addFreag ");

    }
}
