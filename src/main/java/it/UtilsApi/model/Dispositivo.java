package it.UtilsApi.model;

import lombok.*;

import java.util.Date;

/**
 * Classe di esempio - Dispositivo
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dispositivo {
    private String id;
    private String modello;
    private String marca;
    private Integer memoria;
    private Date dataRilascio;

}
