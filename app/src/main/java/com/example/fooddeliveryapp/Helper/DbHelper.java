package com.example.fooddeliveryapp.Helper;

import com.example.fooddeliveryapp.Dao.AppDatabase;
import com.example.fooddeliveryapp.Entity.Cart;
import com.example.fooddeliveryapp.Entity.Category;
import com.example.fooddeliveryapp.Entity.Food;
import com.example.fooddeliveryapp.Entity.Order;
import com.example.fooddeliveryapp.Entity.User;

public class DbHelper {
    public static void initDb(AppDatabase db) {
         User user = new com.example.fooddeliveryapp.Entity.User();
         user.setEmail("admin@gmail.com");
         user.setPassword("admin");
         user.setFullName("Admin");
         user.setAddress("Viet Nam");
         user.setPhoneNumber("0123456789");
         db.userDao().addUser(user);

         // add category
         Category c1 = new Category("Pizza", "cat_1");
         Category c2 = new Category("Burger", "cat_2");
         Category c3 = new Category("HotDog", "cat_3");
         Category c4 = new Category("Drink", "cat_4");
         Category c5 = new Category("Donut", "cat_5");
         db.categoryDao().insertAll(c1, c2, c3, c4, c5);


         Food f1 = new Food("Pepperoni Pizza", "pizza1","slices pepperoni ,mozzarella cheese, pizza sauce, pizza dough",13.0,5,20,1000,0,"Pizza");
         Food f2 = new Food("Burger", "burger","beef, cheese, tomato, onion, lettuce, ketchup, mayonnaise, mustard, pickles",10.0,4,15,800,0,"Burger");
         Food f3 = new Food("Vegetable Pizza", "pizza3","mozzarella cheese, pizza sauce, pizza dough, onion, tomato, mushroom, bell pepper, olive",12.0,4,20,900,0,"Pizza");
         db.foodDao().insertAll(f1, f2, f3);

         Cart cart = new Cart(1,"[{\"calories\":1000,\"category\":\"Pizza\",\"description\":\"slices pepperoni ,mozzarella cheese, pizza sauce, pizza dough\",\"fee\":13.0,\"id\":1,\"numberInCart\":1,\"pic\":\"pizza1\",\"star\":5.0,\"time\":20,\"title\":\"Pepperoni Pizza\"},{\"calories\":800,\"category\":\"Burger\",\"description\":\"beef, cheese, tomato, onion, lettuce, ketchup, mayonnaise, mustard, pickles\",\"fee\":10.0,\"id\":2,\"numberInCart\":2,\"pic\":\"burger\",\"star\":4.0,\"time\":15,\"title\":\"Burger\"}]");
         db.cartDao().insert(cart);

         Order o1 = new Order(1, "[{\"calories\":800,\"category\":\"Burger\",\"description\":\"beef, cheese, tomato, onion, lettuce, ketchup, mayonnaise, mustard, pickles\",\"fee\":10.0,\"id\":2,\"numberInCart\":3,\"pic\":\"burger\",\"star\":4.0,\"time\":15,\"title\":\"Burger\"},{\"calories\":900,\"category\":\"Pizza\",\"description\":\"mozzarella cheese, pizza sauce, pizza dough, onion, tomato, mushroom, bell pepper, olive\",\"fee\":12.0,\"id\":3,\"numberInCart\":3,\"pic\":\"pizza3\",\"star\":4.0,\"time\":20,\"title\":\"Vegetable Pizza\"}]");
         Order o2 = new Order(1, "[{\"calories\":1000,\"category\":\"Pizza\",\"description\":\"slices pepperoni ,mozzarella cheese, pizza sauce, pizza dough\",\"fee\":13.0,\"id\":1,\"numberInCart\":1,\"pic\":\"pizza1\",\"star\":5.0,\"time\":20,\"title\":\"Pepperoni Pizza\"}]");
         db.orderDao().insertAll(o1, o2);
    }
}
