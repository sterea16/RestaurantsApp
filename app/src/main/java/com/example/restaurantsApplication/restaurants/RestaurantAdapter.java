package com.example.restaurantsApplication.restaurants;

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

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ItemViewHolder>{

    private final Context context;
    private final List<Item> items;
    private OnClickListener listener;

    public RestaurantAdapter(List<Item> items, Context context){
        this.items = items;
        this.context = context;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, final int position) {
        Item item = items.get(position);
        holder.title.setText(item.getTitle());
        holder.subtitle.setText(item.getDescription());

        Glide.with(context).load(item.getIcon()).into(holder.icon);
        holder.icon.setContentDescription(context.getResources().getString(R.string.photo_content_description) + (position + 1));

        if(listener != null)
            holder.root.setOnClickListener(v -> listener.onClick(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        private final AppCompatImageView icon;
        private final AppCompatTextView title;
        private final AppCompatTextView subtitle;
        private final View root;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            root = itemView;
            icon = itemView.findViewById(R.id.list_item_icon);
            title = itemView.findViewById(R.id.list_item_title);
            subtitle = itemView.findViewById(R.id.list_item_subTitle);

        }
    }
}
