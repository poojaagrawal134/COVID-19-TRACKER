package com.example.covidtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MycustomAdapter extends ArrayAdapter<CountryModel> {
    private Context context;
    private List<CountryModel> countryModelList;
    private List<CountryModel> countryModelListFiltered;


    public MycustomAdapter(@NonNull Context context, List<CountryModel> countryModelList) {
        super(context, R.layout.listcustomitem,countryModelList);
        this.context=context;
        this.countryModelList=countryModelList;
        this.countryModelListFiltered=countryModelList;

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView , @NonNull ViewGroup parent)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listcustomitem,null,true);
        TextView Tvcountry=view.findViewById(R.id.countryname);
        ImageView Imag = view.findViewById(R.id.imageflag);
        Tvcountry.setText(countryModelListFiltered.get(position).getCountry());
        Glide.with(context).load(countryModelListFiltered.get(position).getFlag()).into(Imag);

        return view;
    }

    @Override
    public int getCount() {
        return countryModelListFiltered.size();
    }

    @Nullable
    @Override
    public CountryModel getItem(int position) {
        return countryModelListFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        Filter filter=new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults=new FilterResults();
                if(constraint==null || constraint.length()==0)
                {
                    filterResults.count=countryModelList.size();
                    filterResults.values=countryModelList;
                }else
                {
                    List<CountryModel> resultmodel =new ArrayList<>();
                    String searchstr=constraint.toString().toLowerCase();
                    for(CountryModel itemsModel:countryModelList)
                    {
                        if(itemsModel.getCountry().toLowerCase().contains(searchstr))
                        {
                            resultmodel.add(itemsModel);
                        }
                        filterResults.count=resultmodel.size();
                        filterResults.values=resultmodel;
                    }
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
               countryModelListFiltered=(List<CountryModel>)results.values;
               Affectedcountry.countryModelList=(List<CountryModel>)results.values;
               notifyDataSetChanged();
            }
        };
        return filter;
    }
}
