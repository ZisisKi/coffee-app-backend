package com.coffeeapp.backend.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CoffeeOrderDTO {

    @NotBlank(message = "Το όνομα δεν μπορεί να είναι κενό")
    private String name;

    @NotBlank(message = "Η διεύθυνση είναι υποχρεωτική")
    private String address;

    @NotBlank(message = "Πρέπει να επιλέξετε αν είναι Hot ή Cold")
    private String type;

    @NotBlank(message = "Πρέπει να επιλέξετε το ρόφημα")
    private String selectedDrink;

    private boolean hasSugar;
    private boolean hasSyrup;
    private boolean isDoubleShot;

    @Min(value = 0, message = "Η γλυκύτητα δεν μπορεί να είναι κάτω από 0")
    @Max(value = 10, message = "Η γλυκύτητα δεν μπορεί να ξεπερνά το 10")
    private double sweetnessLevel;
}