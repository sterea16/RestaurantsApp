package com.example.restaurantsApplication.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.restaurantsApplication.R;
import com.example.restaurantsApplication.model.Item;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{

    private final Context context;
    private List<Item> items;
    private String[] photos;
    private OnClickListener listener;

    public ItemAdapter(List<Item> items, Context context){
        this.items = items;
        this.context = context;
    }

    public ItemAdapter(String[] photos, Context context){
        this.context = context;
        this.photos = photos;
    }

    public interface OnClickListener{
        void onClick(int position);
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(photos != null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant, parent, false);
        }
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, final int position) {
        if(photos != null){
            Glide.with(context).load(photos[position]).into(holder.photoView);
            holder.photoView.setContentDescription(context.getResources().getString(R.string.photo_content_description) + (position + 1));
        } else {
            Item item = items.get(position);
            holder.title.setText(item.getTitle());
            holder.subtitle.setText(item.getDescription());
            Glide.with(context).load(item.getIcon()).into(holder.icon);
            holder.icon.setContentDescription(context.getResources().getString(R.string.photo_content_description) + (position + 1));
        }

        if(listener != null)
            holder.root.setOnClickListener(v -> listener.onClick(position));
    }

    @Override
    public int getItemCount() {
        if(photos != null)
            return photos.length;
        return items.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        private AppCompatImageView icon;
        private AppCompatTextView title;
        private AppCompatTextView subtitle;
        private AppCompatImageView photoView;
        private final View root;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            root = itemView;
            if(photos != null){
                photoView = itemView.findViewById(R.id.details_photo_imageView);
            } else {
                icon = itemView.findViewById(R.id.list_item_icon);
                title = itemView.findViewById(R.id.list_item_title);
                subtitle = itemView.findViewById(R.id.list_item_subTitle);
            }
        }
    }
}
