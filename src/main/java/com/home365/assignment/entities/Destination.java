package com.home365.assignment.entities;


import com.home365.assignment.dto.DestinationDTO;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "destination")
public class Destination {

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

}
