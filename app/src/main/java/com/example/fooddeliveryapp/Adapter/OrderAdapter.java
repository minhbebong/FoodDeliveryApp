package com.example.fooddeliveryapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryapp.Entity.Order;
import com.example.fooddeliveryapp.Fragment.OrderFragment;
import com.example.fooddeliveryapp.Interface.OnItemClickListener;
import com.example.fooddeliveryapp.R;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderHolder>{

    List<Order> orders;
    OnItemClickListener listener;

    public OrderAdapter(List<Order> orders,OnItemClickListener listener) {
        this.orders = orders;
        this.listener = listener;
    }

    @NonNull
    @Override
    public OrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_order,parent,false);
        return new OrderHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHolder holder, int position) {
        holder.orderId.setText("Order#: "+orders.get(position).getId().substring(0,6));
        holder.orderDate.setText(orders.get(position).getDate());
        holder.orderTotal.setText("$"+orders.get(position).totalBill());
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    class OrderHolder extends RecyclerView.ViewHolder{
        TextView orderId;
        TextView orderDate;
        TextView orderTotal;
        public OrderHolder(@NonNull View itemView) {
            super(itemView);
            orderId = itemView.findViewById(R.id.order_id);
            orderDate = itemView.findViewById(R.id.order_date);
            orderTotal = itemView.findViewById(R.id.order_total);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(orders.get(getAdapterPosition()));
                }
            });
        }
    }
}
