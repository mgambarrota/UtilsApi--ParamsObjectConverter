package it.betacom.UtilsApi.controller;

import it.betacom.UtilsApi.factory.ObjectFactoryService;
import it.betacom.UtilsApi.genericUtils.GenericUtilsService;
import it.betacom.UtilsApi.model.Dispositivo;
import it.betacom.UtilsApi.model.Persona;
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

    @PostMapping(value = "/convertDispositivoToJson", produces = MediaType.APPLICATION_JSON_VALUE)
    public String convertDispositivoToJson(@RequestParam Map<String, Object> params) {
        Dispositivo dispositivo = (Dispositivo)factory.createObject(params, Dispositivo.class);
        if(dispositivo != null){
            dispositivo.setId((dispositivo.getId() == null) || (dispositivo.getId().isEmpty()) ? UUID.randomUUID().toString() : dispositivo.getId());
        }
        return utils.convertToJson(dispositivo);
    }

    @PostMapping(value = "/convertPersonaToJson", produces = MediaType.APPLICATION_JSON_VALUE)
    public String convertPersonaToJson(@RequestParam Map<String, Object> params) {
        Persona persona = (Persona)factory.createObject(params, Persona.class);
        if(persona != null){
            persona.setId((persona.getId() == null) || (persona.getId().isEmpty()) ? UUID.randomUUID().toString() : persona.getId());
        }
        return utils.convertToJson(persona);
    }

}
