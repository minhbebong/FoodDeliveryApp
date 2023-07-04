package com.example.fooddeliveryapp.Entity;


public class Category {

    private String id;

    private String title;

    private String pic;


    public Category() {
    }

    public Category(String title, String pic) {
        this.title = title;
        this.pic = pic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
