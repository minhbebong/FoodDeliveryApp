package com.example.fooddeliveryapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooddeliveryapp.Domain.FoodDomain;
import com.example.fooddeliveryapp.R;

import java.util.ArrayList;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.RecommendedHolder>{

    ArrayList<FoodDomain> foodDomains;

    public RecommendedAdapter(ArrayList<FoodDomain> categoryDomains) {
        this.foodDomains = categoryDomains;
    }


    @NonNull
    @Override
    public RecommendedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_recommended, parent, false);
        return new RecommendedHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedHolder holder, int position) {
        FoodDomain foodDomain = foodDomains.get(position);
        holder.title.setText(foodDomain.getTitle());
        holder.fee.setText(foodDomain.getFee().toString());
        int drawableResourceId = holder.itemView.getContext()
                .getResources().getIdentifier(foodDomain.getPic(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.pic);
    }

    @Override
    public int getItemCount() {
        return foodDomains.size();
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
