package com.example.musicapp.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.musicapp.Models.SearchModel;
import com.example.musicapp.R;

import java.util.ArrayList;

public class SearchAdapter extends ArrayAdapter<SearchModel> implements Filterable {
    Activity context;
    int Idlayout;
    ArrayList<SearchModel> mylist;
    ArrayList<SearchModel> filteredList;


    public SearchAdapter(Activity context, int idlayout, ArrayList<SearchModel> mylist) {
        super(context, idlayout, mylist);
        this.context = context;
        Idlayout = idlayout;
        this.mylist = new ArrayList<>(mylist);
        this.filteredList = new ArrayList<>(mylist);
    }



    private static class ViewHolder {
        ImageView img_item;
        TextView txt_name;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(Idlayout, parent, false);

            holder = new ViewHolder();
            holder.img_item = convertView.findViewById(R.id.img_item);
            holder.txt_name = convertView.findViewById(R.id.txt_name);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        SearchModel myitem = getItem(position);
        if (myitem != null) {
            holder.img_item.setImageResource(myitem.getImage());
            holder.txt_name.setText(myitem.getName());
        }

        return convertView;
    }
    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String filterPattern = constraint.toString().toLowerCase().trim();
                filteredList.clear();

                for (SearchModel currentItem : mylist) {
                    if (currentItem.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(currentItem);
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filteredList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                clear();
                addAll((ArrayList<SearchModel>) results.values);
                notifyDataSetChanged();
            }
        };
    }
}
