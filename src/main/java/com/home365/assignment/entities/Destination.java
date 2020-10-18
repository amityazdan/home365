package com.home365.assignment.entities;


import com.home365.assignment.dto.DestinationDTO;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "destination")
public class Destination {

    public Destination() {
    }

    public Destination(DestinationDTO destinationDTO) {
        this.name = destinationDTO.getName();
        this.latitude = destinationDTO.getLatitude();
        this.longitude = destinationDTO.getLongitude();
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "latitude")
    private double latitude;

    @NotNull
    @Column(name = "longitude")
    private double longitude;



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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
