package com.home365.assignment.entities;


import com.home365.assignment.dto.AircraftDTO;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "aircraft")
public class Aircraft {

    public Aircraft() {
    }

    public Aircraft(AircraftDTO aircraftDTO) {
        this.name = aircraftDTO.getName();
        this.maxDistance = aircraftDTO.getMaxDistance();
        this.price = aircraftDTO.getPrice();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "price")
    private int price;

    @NotNull
    @Column(name = "max_distance")
    private int maxDistance;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
