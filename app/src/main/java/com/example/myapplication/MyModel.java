package com.example.myapplication;

public class MyModel {

    public int photo;
    private String text;

    private boolean isSelected;


    public MyModel (int photo, String text){
        this.photo = photo;
        this.text = text;
        this.isSelected = false;

    }

    public int getPhoto() {
        return photo;
    }

    public String getText() {
        return text;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }



}
