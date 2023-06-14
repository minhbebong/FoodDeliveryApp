package com.example.fooddeliveryapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooddeliveryapp.Domain.FoodDomain;
import com.example.fooddeliveryapp.Helper.ManagementCart;
import com.example.fooddeliveryapp.Interface.ChangeNumberItemsListener;
import com.example.fooddeliveryapp.R;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.CartListHolder>{

    ArrayList<FoodDomain> foodDomains;
    private ManagementCart managementCart;
    ChangeNumberItemsListener changeNumberItemsListener;

    public CartListAdapter(ArrayList<FoodDomain> categoryDomains, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.foodDomains = categoryDomains;
        this.managementCart = new ManagementCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
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
        FoodDomain foodDomain = foodDomains.get(position);
        holder.title.setText(foodDomain.getTitle());
        holder.feeEachItem.setText("$" + foodDomain.getFee().toString());
        holder.totalEachItem.setText("$" + Math.round(foodDomain.getFee() * foodDomain.getNumberInCart() * 100.0) / 100.0);
        holder.num.setText(foodDomain.getNumberInCart() + "");
        int drawableResourceId = holder.itemView.getContext()
                .getResources().getIdentifier(foodDomain.getPic(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.pic);

        holder.plusBtn.setOnClickListener(v ->{
            managementCart.plusNumberFood(foodDomains, position, ()->{
                notifyDataSetChanged();
                changeNumberItemsListener.changed();
            });
        });

        holder.minusBtn.setOnClickListener(v ->{
            managementCart.minusNumberFood(foodDomains, position, ()->{
                notifyDataSetChanged();
                changeNumberItemsListener.changed();
            });
        });
    }

    @Override
    public int getItemCount() {
        return foodDomains.size();
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
