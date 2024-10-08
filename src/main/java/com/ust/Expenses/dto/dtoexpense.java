package com.ust.Expenses.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class dtoexpense {



    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Name should not exceed 100 characters")
    private String name;

    @NotBlank(message = "Transaction method is mandatory")
    @Size(max = 50, message = "Transaction method should not exceed 50 characters")
    private String transcationmethod;

    @NotNull(message = "Amount is mandatory")
    @Positive(message = "Amount should be positive")
    private Double amount;

    @NotBlank(message = "Description is mandatory")
    @Size(max = 255, message = "Description should not exceed 255 characters")
    private String description;

    @NotBlank(message = "Type is mandatory")
    private String type;

    private LocalDate createDate;

}
