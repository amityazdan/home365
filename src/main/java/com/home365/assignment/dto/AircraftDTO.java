package com.home365.assignment.dto;


import javax.validation.constraints.NotBlank;

public class AircraftDTO {

    @NotBlank
    private String name;
    @NotBlank
    private int price;
    @NotBlank
    private int maxDistance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(int maxDistance) {
        this.maxDistance = maxDistance;
    }
}
