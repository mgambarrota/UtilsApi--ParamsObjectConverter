package it.UtilsApi.controller;

import it.UtilsApi.model.Persona;
import it.UtilsApi.factory.ObjectFactoryService;
import it.UtilsApi.genericUtils.GenericUtilsService;
import it.UtilsApi.model.Dispositivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/gson")
public class GsonController {
    @Autowired
    private GenericUtilsService utils;

    @Autowired
    private ObjectFactoryService factory;

    /**
     * Metodo che riceve una Map di parametri e li converte in un oggetto della classe Dispositivo (classe esempio).
     * @param params Lista di parametri che le cui keys concidono con i nomi dei parametri della classe Dispositivo
     * @return String Json
     */
    @PostMapping(value = "/convertDispositivoToJson", produces = MediaType.APPLICATION_JSON_VALUE)
    public String convertDispositivoToJson(@RequestParam Map<String, Object> params) {
        Dispositivo dispositivo = (Dispositivo)factory.createObject(params, Dispositivo.class);
        if(dispositivo != null){
            dispositivo.setId((dispositivo.getId() == null) || (dispositivo.getId().isEmpty()) ? UUID.randomUUID().toString() : dispositivo.getId());
        }
        return utils.convertToJson(dispositivo);
    }

    /**
     * Metodo che riceve una Map di parametri e li converte in un oggetto della classe Persona (classe esempio).
     * @param params Lista di parametri che le cui keys concidono con i nomi dei parametri della classe Persona
     * @return String Json
     */
    @PostMapping(value = "/convertPersonaToJson", produces = MediaType.APPLICATION_JSON_VALUE)
    public String convertPersonaToJson(@RequestParam Map<String, Object> params) {
        Persona persona = (Persona)factory.createObject(params, Persona.class);
        if(persona != null){
            persona.setId((persona.getId() == null) || (persona.getId().isEmpty()) ? UUID.randomUUID().toString() : persona.getId());
        }
        return utils.convertToJson(persona);
    }

}
