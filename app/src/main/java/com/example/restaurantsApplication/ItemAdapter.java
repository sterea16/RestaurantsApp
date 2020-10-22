package com.example.restaurantsApplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.restaurantsApplication.model.Item;
import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{

    private ArrayList<Item> items;
    private Context context;
    private boolean forDetailsActivity;
    private OnClickListener listener;
    private String[] photos;

    public ItemAdapter(ArrayList<Item> items, Context context, boolean isDetailsActivity){
        this.items = items;
        this.context = context;
        this.forDetailsActivity = isDetailsActivity;
    }

    public ItemAdapter(String[] photos, Context context, boolean forDetailsActivity){
        this.context = context;
        this.photos = photos;
        this.forDetailsActivity = forDetailsActivity;
    }

    public interface OnClickListener{
        void onClick(int position);
    }

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(forDetailsActivity) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant, parent, false);
        }
        return new ItemViewHolder(view, forDetailsActivity);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, final int position) {
        if(forDetailsActivity){
            Glide.with(context).load(photos[position]).into(holder.photo);
            holder.photo.setContentDescription("Photo " + (position + 1));
        } else {
            Item item = items.get(position);
            holder.title.setText(item.getTitle());
            holder.subtitle.setText(item.getSubtitle());
            Glide.with(context).load(item.getIcon()).into(holder.icon);
        }

        if(listener != null)
            holder.root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(position);
                }
            });
    }

    @Override
    public int getItemCount() {
        if(forDetailsActivity)
            return photos.length;
        return items.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        private AppCompatImageView icon;
        private AppCompatTextView title;
        private AppCompatTextView subtitle;
        private AppCompatImageView photo;
        private View root;

        public ItemViewHolder(@NonNull View itemView, boolean requireItemPhoto) {
            super(itemView);
            root = itemView;
            if(requireItemPhoto){
                photo = itemView.findViewById(R.id.details_photo_imageView);
            } else {
                icon = itemView.findViewById(R.id.list_item_icon);
                title = itemView.findViewById(R.id.list_item_title);
                subtitle = itemView.findViewById(R.id.list_item_subTitle);
            }
        }
    }
}
