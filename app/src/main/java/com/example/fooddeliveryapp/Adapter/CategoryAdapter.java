package com.example.fooddeliveryapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooddeliveryapp.Entity.Category;
import com.example.fooddeliveryapp.Fragment.HomeFragment;
import com.example.fooddeliveryapp.Interface.OnItemClickListener;
import com.example.fooddeliveryapp.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder>{

    List<Category> categories;
    OnItemClickListener listener;

    public CategoryAdapter(List<Category> categories, OnItemClickListener<String> listener) {
        this.categories = categories;
        this.listener = listener;

    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category,parent,false);

        return new CategoryHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        holder.categoryName.setText(categories.get(position).getTitle());
        int drawableResourceId = holder.itemView.getContext()
                .getResources().getIdentifier(categories.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.categoryImage);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class CategoryHolder extends RecyclerView.ViewHolder{
        TextView categoryName;
        ImageView categoryImage;
        ConstraintLayout mainLayout;
        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.category_txt);
            categoryImage = itemView.findViewById(R.id.category_pic);
            mainLayout = itemView.findViewById(R.id.main_layout);
            itemView.setOnClickListener(v -> listener.onItemClick(categories.get(getAdapterPosition()).getTitle()));
        }
    }
}
