package com.example.fooddeliveryapp.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.fooddeliveryapp.Entity.Food;
import com.example.fooddeliveryapp.Interface.ChangeNumberItemsListener;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertFood(Food food){
        ArrayList<Food> listFood = getListCart();
        boolean exist = false;
        int n = 0;
        for (int i =0; i < listFood.size(); i++){
            if(listFood.get(i).getTitle().equals(food.getTitle())){
                exist = true;
                n = i;
            }
        }
        if (exist) {
            listFood.get(n).setNumberInCart(listFood.get(n).getNumberInCart());
        }else {
            food.setNumberInCart(1);
            listFood.add(food);
        }

        tinyDB.putObject("CardList", listFood);
        Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<Food> getListCart() {
        return tinyDB.getListObject("CardList");
    }

    public void minusNumberFood(ArrayList<Food> listFood, int position, ChangeNumberItemsListener changeNumberItemsListener){
         if(listFood.get(position).getNumberInCart() == 1) {
             listFood.remove(position);
         }else {
             listFood.get(position).setNumberInCart(listFood.get(position).getNumberInCart() - 1);
         }
         tinyDB.putListObject("CardList", listFood);
         changeNumberItemsListener.changed();
    }

    public void plusNumberFood(ArrayList<Food> listFood, int position, ChangeNumberItemsListener changeNumberItemsListener){
        listFood.get(position).setNumberInCart(listFood.get(position).getNumberInCart() + 1);
        tinyDB.putListObject("CardList", listFood);
        changeNumberItemsListener.changed();
    }

    public Double getTotalFee(){
        ArrayList<Food> listFood = getListCart();
        Double total = 0.0;
        for (int i = 0; i < listFood.size(); i++){
            total += listFood.get(i).getNumberInCart() * listFood.get(i).getFee();
        }
        return total;
    }
}
