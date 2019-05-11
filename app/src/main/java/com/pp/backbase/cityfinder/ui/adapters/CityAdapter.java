package com.pp.backbase.cityfinder.ui.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.pp.backbase.cityfinder.R;
import com.pp.backbase.cityfinder.base.MainApplication;
import com.pp.backbase.cityfinder.models.CityItemModel;
import com.pp.backbase.cityfinder.ui.activities.MainActivity;
import com.pp.backbase.cityfinder.ui.fragment.CityFinderFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Praful Pijdurkar on 5/7/19.
 */
public class CityAdapter extends ArrayAdapter<CityItemModel> implements Filterable {

    private Context mContext;
    private List<CityItemModel> cityList = new ArrayList<>();
    private List<CityItemModel> filteredCityList = new ArrayList<>();
    private ItemFilter mFilter = new ItemFilter();
    private LayoutInflater mInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    CityFinderFragment fragment;


    public CityAdapter(@NonNull CityFinderFragment fragment, List<CityItemModel> list) {
        super(MainApplication.applicationContext, 0 , list);
        this.fragment = fragment;
        filteredCityList = cityList = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
       final CityItemModel cityItemModel = getItem(position);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.city_item, null);
            holder = new ViewHolder();
            holder.tv_title = (TextView) convertView.findViewById(R.id.title);
            holder.tv_subTitle = (TextView) convertView.findViewById(R.id.subtitle);
            holder.aboutbutton = (Button) convertView.findViewById(R.id.aboutbutton);

            convertView.setTag(holder);


        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment.getHomePresenter() .showMapFragment(cityItemModel);

            }
        });

        setBG(position,convertView);

        holder.tv_title.setText(cityItemModel.getName() + ", " +cityItemModel.getCountry());
        holder.tv_subTitle.setText(cityItemModel.getCoordinatesModel().getLat() + "," + cityItemModel.getCoordinatesModel().getLon());
        holder.aboutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.getHomePresenter().getShowAbout(cityItemModel);

            }
        });

        return convertView;

    }


    private void setBG(int position, View view) {
        if (position % 2 == 1) {
            view.setBackgroundColor(Color.WHITE);
        } else {
            view.setBackgroundColor(getContext().getResources().getColor(R.color.light_gray));
        }
     }

     @Override
    public int getCount() {
        return filteredCityList.size();
    }

    @Override
    public CityItemModel getItem(int position) {
        return filteredCityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    static class ViewHolder {
        private TextView tv_title;
        private TextView tv_subTitle;
        private Button aboutbutton;

     }

    public List<CityItemModel> getCityList() {
        return cityList;
    }

    public Filter getFilter() {
        return mFilter;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<CityItemModel> list = cityList;

            int count = list.size();
            final ArrayList<CityItemModel> nlist = new ArrayList<CityItemModel>(count);

            CityItemModel cityItemModel ;

            for (int i = 0; i < count; i++) {
                cityItemModel = list.get(i);
                if (cityItemModel.getName().toLowerCase().startsWith(filterString)) {
                    nlist.add(cityItemModel);
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredCityList = (ArrayList<CityItemModel>) results.values;
            notifyDataSetChanged();
        }

    }


    }
