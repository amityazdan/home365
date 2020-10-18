package com.home365.assignment.entities;


import com.home365.assignment.dto.AircraftDTO;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
@Table(name = "aircraft")
public class Aircraft {

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
}
