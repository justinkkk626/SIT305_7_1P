package com.example.a7_1justin;

public class Item {
    private int id;
    private String title;
    private String desc;
    private String date;
    private String location;
    private String contact;

    public Item(int id, String title, String desc, String date, String location, String contact) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.date = date;
        this.location = location;
        this.contact = contact;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public String getContact() {
        return contact;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
