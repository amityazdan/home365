package com.home365.assignment.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;

@Entity
@Data
@NoArgsConstructor
@Table(name = "airline_aircraft")
public class AirlineAircraft {

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


    public int getSellPrice() {
//        original price * (1 - num of months aircraft in use*0.02)
        long monthsBetween = ChronoUnit.MONTHS.between(
                YearMonth.from(boughtDate),
                YearMonth.from(LocalDateTime.now()));

        return (int) (aircraft.getPrice() * (1 - (monthsBetween * 0.02)));
    }
}
