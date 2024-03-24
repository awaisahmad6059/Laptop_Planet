package com.awais.laptopplanet;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Laptop {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String type;
    private String specification;
    private String price;
    private int image;
    boolean isSold=false;
    boolean isDeleted=false;
    private String status="In Stock";

    public Laptop() {
    }

    public Laptop(String name, String type, String specification, String price, int image) {
        this.name = name;
        this.type = type;
        this.specification = specification;
        this.price = price;
        this.image = image;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
