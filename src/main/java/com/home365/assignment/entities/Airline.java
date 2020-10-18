package com.home365.assignment.entities;


import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "airline")
public class Airline {


    public Airline(String name, long balance, Destination homeBase) {
        this.name = name;
        this.balance = balance;
        this.homeBase = homeBase;
        this.createdDate = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "balance")
    private long balance;

    @ManyToOne
    @JoinColumn(name = "home_base_id")
    private Destination homeBase;

    @NotNull
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "airline")
    private Set<AirlineAircraft> airlineAircraft;

    public boolean updateBudget(int change) {
        this.balance += change;
        return this.balance > 0;
    }
}
