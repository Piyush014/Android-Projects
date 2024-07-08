package com.piyush.a20_recycler_view;

public class ContactModel {
    int img;
    String name, number;

    public ContactModel(int img, String name, String number) {
        this.img = img;
        this.name = name;
        this.number = number;
    }

    public int getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }
}
