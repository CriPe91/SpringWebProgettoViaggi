package com.example.SpringWebProgetto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class ViaggioDTO {



    @NotNull(message = "La destinazione è un campo obbligatorio")
    @NotBlank(message = "La destinazione risulta vuoto")
    private String destinazione;


    @NotNull(message = "La data del viaggio è un campo obbligatorio")
    @NotBlank(message = "La data del viaggio risulta vuoto")
    private LocalDate dataViaggio;

    @NotBlank(message = "Lo stato del viaggio risulta vuoto")
    private String statoViaggio;



}
