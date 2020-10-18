package com.home365.assignment.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "airline_aircraft")
public class AirlineAircraft {

    public AirlineAircraft() {
    }

    public AirlineAircraft(Airline airline, Aircraft aircraft) {
        this.airline = airline;
        this.aircraft = aircraft;
        this.boughtDate = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "airline_id")
    private Airline airline;

    @ManyToOne
    @JoinColumn(name = "aircraft_id")
    private Aircraft aircraft;


    @Column(name = "bought_date")
    public LocalDateTime boughtDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public LocalDateTime getBoughtDate() {
        return boughtDate;
    }

    public void setBoughtDate(LocalDateTime boughtDate) {
        this.boughtDate = boughtDate;
    }

    public int getSellPrice() {
//        original price * (1 - num of mfonths aircraft in use*0.02)
        long monthsBetween = ChronoUnit.MONTHS.between(
                YearMonth.from(boughtDate),
                YearMonth.from(LocalDateTime.now()));

        return (int) (aircraft.getPrice() * (1 - (monthsBetween * 0.02)));
    }
}
