package com.example.fooddeliveryapp.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooddeliveryapp.Entity.Food;
import com.example.fooddeliveryapp.R;
import com.example.fooddeliveryapp.Interface.CartChange;

import java.util.List;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.CartListHolder>{

    List<Food> Foods;
    CartChange cartExecute;

    public CartListAdapter(List<Food> categoryDomains , CartChange cartExecute) {
        this.Foods = categoryDomains;
        this.cartExecute = cartExecute;
    }


    @NonNull
    @Override
    public CartListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new CartListHolder(inflate);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull CartListHolder holder, int position) {
        Food food = Foods.get(position);
        holder.title.setText(food.getTitle());
        holder.feeEachItem.setText("$" + food.getFee().toString());
        holder.totalEachItem.setText("$" + Math.round(food.getFee() * food.getNumberInCart() * 100.0) / 100.0);
        holder.num.setText(food.getNumberInCart() + "");
        int drawableResourceId = holder.itemView.getContext()
                .getResources().getIdentifier(food.getPic(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.pic);

        holder.plusBtn.setOnClickListener(v ->{
            food.setNumberInCart(food.getNumberInCart() + 1);
            cartExecute.execute(food);
            notifyDataSetChanged();
        });

        holder.minusBtn.setOnClickListener(v ->{
            food.setNumberInCart(food.getNumberInCart() - 1);
            if (food.getNumberInCart() == 0){
                Foods.remove(position);
            }
            cartExecute.execute(food);
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return Foods.size();
    }


    class CartListHolder extends RecyclerView.ViewHolder{
        TextView title,feeEachItem;
        ImageView pic,minusBtn,plusBtn;
        TextView totalEachItem,num;
        public CartListHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTxt);
            pic = itemView.findViewById(R.id.cartItemPic);
            feeEachItem = itemView.findViewById(R.id.feeEachItem);
            minusBtn = itemView.findViewById(R.id.minusCartBtn);
            plusBtn = itemView.findViewById(R.id.plusCartBtn);
            totalEachItem = itemView.findViewById(R.id.totalEachItem);
            num = itemView.findViewById(R.id.numberItemTxt);
        }
    }
}
