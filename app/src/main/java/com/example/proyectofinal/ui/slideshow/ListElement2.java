package com.example.proyectofinal.ui.slideshow;


import java.io.Serializable;

public class ListElement2 implements Serializable {
    public String Color;
    public String name;
    public String city;
    public String status;


    public ListElement2(String color, String name, String city, String status) {
        Color = color;
        this.name = name;
        this.city = city;
        this.status = status;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
