package com.example.SpringWebProgetto.dto;

import com.example.SpringWebProgetto.entity.Prenotazioni;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.URL;


@Data
public class DipendenteDTO {

    @NotNull(message = "Il nome è un campo obbligatorio")
    @Size(min = 3, message = "Nome troppo corto")
    @Size(max = 20, message = "Nome troppo lungo")
    private String nome;

    @NotNull(message = "Il cognome è un campo obbligatorio")
    @NotBlank(message = "Il cognome risulta vuoto")
    @Size(min = 3, message = "Cognome troppo corto")
    @Size(max = 20, message = "Cognome  troppo lungo")
    private String cognome;

    @NotNull(message = "Il tuo username è un campo obbligatorio")
    @NotBlank(message = "Il campo username risulta vuoto")
    private String username;

    @Email(message = "L'indirizzo mail non è valido")
    private String email;

    @URL(protocol ="https", message="Immagine non disponibile" )
    private String imgProfilo;

}
