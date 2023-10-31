package com.example.restproject;

import jakarta.ws.rs.core.GenericEntity;

import java.util.ArrayList;
import java.util.List;

public class Bicycle {
    public String name;
    public int speeds;

    public List<BikeShop> shopList;

    public Bicycle(String name, int speeds) {
        this.name = name;
        this.speeds = speeds;
        shopList = new ArrayList<BikeShop>();
    }

    public Bicycle() {

    }
}