package com.example.fooddeliveryapp.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooddeliveryapp.Activity.ShowDetailActivity;
import com.example.fooddeliveryapp.Entity.Food;
import com.example.fooddeliveryapp.R;

import java.util.ArrayList;
import java.util.List;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.RecommendedHolder>{

    List<Food> foods;

    public RecommendedAdapter(List<Food> foods) {
        this.foods = foods;
    }


    @NonNull
    @Override
    public RecommendedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_recommended, parent, false);
        return new RecommendedHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedHolder holder, int position) {
        Food Food = foods.get(position);
        holder.title.setText(Food.getTitle());
        holder.fee.setText(Food.getFee().toString());
        int drawableResourceId = holder.itemView.getContext()
                .getResources().getIdentifier(Food.getPic(), "drawable", holder.itemView.getContext().getPackageName());
        //get image Resource in drawable folder
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.pic);

        holder.addBtn.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
            intent.putExtra("fid", Food.getId());
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }


    class RecommendedHolder extends RecyclerView.ViewHolder{
        TextView title,fee;
        ImageView pic;
        ImageView addBtn;

        public RecommendedHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.rec_title);
            fee = itemView.findViewById(R.id.fee);
            pic = itemView.findViewById(R.id.pic);
            addBtn = itemView.findViewById(R.id.addBtn);

        }
    }
}
