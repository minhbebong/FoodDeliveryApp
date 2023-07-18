package com.example.fooddeliveryapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooddeliveryapp.Entity.Food;
import com.example.fooddeliveryapp.Entity.Order;
import com.example.fooddeliveryapp.Interface.OnItemClickListener;
import com.example.fooddeliveryapp.R;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.OrderHolder>{

    List<Food> foods;
    OnItemClickListener listener;

    public FoodAdapter(List<Food> foods, OnItemClickListener listener) {
        this.foods = foods;
        this.listener = listener;
    }

    @NonNull
    @Override
    public OrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_food,parent,false);
        return new OrderHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHolder holder, int position) {
        Food food = foods.get(position);
        holder.title.setText(food.getTitle());
        holder.feeEachItem.setText("$" + food.getFee().toString());
        int drawableResourceId = holder.itemView.getContext()
                .getResources().getIdentifier(food.getPic(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.pic);
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    class OrderHolder extends RecyclerView.ViewHolder{
        TextView title,feeEachItem;
        ImageView pic;
        public OrderHolder(@NonNull View itemView) {
            super(itemView);
            pic = itemView.findViewById(R.id.cartItemPic);
            title = itemView.findViewById(R.id.titleTxt);
            feeEachItem = itemView.findViewById(R.id.totalEachItem);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(foods.get(getAdapterPosition()));
                }
            });
        }
    }
}
