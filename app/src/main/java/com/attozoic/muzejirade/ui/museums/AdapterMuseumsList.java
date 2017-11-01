package com.attozoic.muzejirade.ui.museums;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.attozoic.muzejirade.R;
import com.attozoic.muzejirade.model.Museum;
import com.attozoic.muzejirade.model.iListItem;
import com.attozoic.muzejirade.utils.OnRecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nenadicivan on 10/11/2017.
 */

public class AdapterMuseumsList extends RecyclerView.Adapter<AdapterMuseumsList.ViewHolder> {

    private List<iListItem> listOfMuseums;
    private OnRecyclerItemClickListener itemClickListener;


    public AdapterMuseumsList(OnRecyclerItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
        this.listOfMuseums = new ArrayList();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_of_museums_item,parent,false);

        return new AdapterMuseumsList.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
          Museum museum = (Museum) listOfMuseums.get(position);
        holder.textViewTitle.setText(museum.getName() + " >>");

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClick(museum);
            }
        });

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
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativeLayoutMuseumPost);
            textViewTitle = (TextView) itemView.findViewById(R.id.museum_item_name);
        }
    }


}

