package com.example.rengoring.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Supply")
public class Supply {
    @PrimaryKey(autoGenerate = true)
    private String name;
    private int quantity;

    public Supply(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

}
