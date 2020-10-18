package com.home365.assignment.dto;


public class DistancesDTO {

    public DistancesDTO() {
    }

    public DistancesDTO(String name, int distance) {
        this.name = name;
        this.distance = distance;
    }

    private String name;
    private int distance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
