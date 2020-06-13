package com.example.rengoring.Model;

import java.util.ArrayList;

public class Request {
    private ArrayList<Supply> supplies;
    private String name;
    private boolean expanded;


    public Request(String name, ArrayList<Supply> supplies) {
        this.supplies = supplies;
        this.name = name;
        this.expanded = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Supply> getSupplies() {
        return supplies;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

}
