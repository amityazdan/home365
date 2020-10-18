package com.home365.assignment.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class AircraftDTO {

    @NotBlank
    private String name;
    @NotBlank
    private int price;
    @NotBlank
    private int maxDistance;
}
