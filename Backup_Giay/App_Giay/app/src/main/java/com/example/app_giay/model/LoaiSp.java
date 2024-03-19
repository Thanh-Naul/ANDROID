package com.example.app_giay.model;

public class LoaiSp {
    int id;
    String name;
    String hinhanh;

    public LoaiSp(String name, String hinhanh) {
        this.name = name;
        this.hinhanh = hinhanh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }
}
