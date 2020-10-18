package com.home365.assignment.entities;


import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "airline")
public class Airline {


    public Airline() {
    }

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

    public Set<AirlineAircraft> getAirlineAircraft() {
        return airlineAircraft;
    }

    public void setAirlineAircraft(Set<AirlineAircraft> airlineAircraft) {
        this.airlineAircraft = airlineAircraft;
    }

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

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public Destination getHomeBase() {
        return homeBase;
    }

    public void setHomeBase(Destination homeBase) {
        this.homeBase = homeBase;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public boolean updateBudget(int change) {
        this.balance += change;
        return this.balance > 0;
    }
}
