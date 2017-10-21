package com.attozoic.muzejirade.ui.posts;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.attozoic.muzejirade.R;
import com.attozoic.muzejirade.model.Post;
import com.attozoic.muzejirade.model.iListItem;
import com.attozoic.muzejirade.utils.OnRecyclerItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nenadicivan on 9/12/2017.
 */

public class AdapterPosts extends RecyclerView.Adapter<AdapterPosts.ViewHolder> {//klasa adapter

    private List<iListItem> listItems;
    private OnRecyclerItemClickListener itemClickListener;

    public List<iListItem> getListItems() {
        return listItems;
    }

    public AdapterPosts(OnRecyclerItemClickListener lisener) {
        this.listItems = new ArrayList<>();
        this.itemClickListener = lisener;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View v = LayoutInflater.from(parent.getContext())
              .inflate(R.layout.list_item,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Post post = (Post)listItems.get(position);

        holder.textViewCategory.setText(post.getCategory().getName());
        holder.textViewTitle.setText(post.getTitle());
        Picasso.with(holder.imageView.getContext())
                .load(post.getMainImageUrl())
                .into(holder.imageView);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //Toast.makeText(v.getContext(),"kliknuo si na " + listItem.getHead(),Toast.LENGTH_LONG).show();
                  itemClickListener.onItemClick( post);
            }
        });

    }


    public void updateListItems(List<iListItem> items){
        this.listItems.clear();
        this.listItems.addAll(items);
        notifyDataSetChanged();
    }

    public void loadMoreListItems(List<iListItem> items){
        this.listItems.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{//A ViewHolder describes an item view and metadata about its place within the RecyclerView.
                                                            //vju holder opisuje item vju i metapodatke o njegovoj poziciju u okviru recajklervjua

        public TextView textViewCategory;
        public TextView textViewTitle;
        public ImageView imageView;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewCategory = (TextView)itemView.findViewById(R.id.textViewCategory);
            textViewTitle = (TextView)itemView.findViewById(R.id.textViewTitle);
            imageView = (ImageView)itemView.findViewById(R.id.imageView);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativeLayoutPost);

        }
    }
}
