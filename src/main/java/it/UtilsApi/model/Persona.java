package it.UtilsApi.model;

import lombok.*;

import java.util.Date;

/**
 * Classe di esempio - Persona
 */

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Persona {
    private String id;
    private String nome;
    private String cognome;
    private Date data_nascita;
}
