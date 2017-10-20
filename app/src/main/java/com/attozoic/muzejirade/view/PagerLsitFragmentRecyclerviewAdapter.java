package com.attozoic.muzejirade.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.attozoic.muzejirade.R;
import com.attozoic.muzejirade.model.Museum;
import com.attozoic.muzejirade.model.iListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nenadicivan on 10/11/2017.
 */

public class PagerLsitFragmentRecyclerviewAdapter extends RecyclerView.Adapter<PagerLsitFragmentRecyclerviewAdapter.ViewHolder> {

       List listOfMuseums;

    public PagerLsitFragmentRecyclerviewAdapter() {
        this.listOfMuseums = new ArrayList();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_of_museums_item,parent,false);

        return new PagerLsitFragmentRecyclerviewAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
          Museum m = (Museum) listOfMuseums.get(position);
        holder.textViewTitle.setText(m.getName());

    }

    @Override
    public int getItemCount() {
        return listOfMuseums.size();
    }

    public void updateListItems(List listOfMuseums){
        this.listOfMuseums = listOfMuseums;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewTitle;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewTitle = (TextView) itemView.findViewById(R.id.museum_item_name);
        }
    }


}

