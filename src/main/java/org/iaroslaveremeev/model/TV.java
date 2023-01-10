package org.iaroslaveremeev.model;

import java.util.Objects;

public class TV {
    private int id;
    private String brand;
    private String model;
    private String color;
    private int timeExpectancy;
    private double price;

    public TV() {
    }

    public TV(int id, String brand, String model, String color, int timeExpectancy, double price) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.timeExpectancy = timeExpectancy;
        this.price = price;
    }

    public TV(String brand, String model, String color, int timeExpectancy, double price) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.timeExpectancy = timeExpectancy;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getTimeExpectancy() {
        return timeExpectancy;
    }

    public void setTimeExpectancy(int timeExpectancy) {
        this.timeExpectancy = timeExpectancy;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TV tv = (TV) o;
        return id == tv.id && timeExpectancy == tv.timeExpectancy && Double.compare(tv.price, price) == 0 && Objects.equals(brand, tv.brand) && Objects.equals(model, tv.model) && Objects.equals(color, tv.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model, color, timeExpectancy, price);
    }

    @Override
    public String toString() {
        return "TV{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", timeExpectancy=" + timeExpectancy +
                ", price=" + price +
                '}';
    }
}
