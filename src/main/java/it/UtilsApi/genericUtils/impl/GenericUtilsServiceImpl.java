package it.UtilsApi.genericUtils.impl;

import com.google.gson.Gson;
import it.UtilsApi.genericUtils.GenericUtilsService;
import org.springframework.stereotype.Service;

@Service
public class GenericUtilsServiceImpl implements GenericUtilsService {
    private final Gson gsonClass = new Gson();

    /**
     * Utilizza la classe Gson per convertire un oggetto in stringa Json
     * @param object - Oggetto qualunque
     * @return - Ritorna la stringa Json dell'oggetto
     */
    @Override
    public String convertToJson(Object object) {
        return gsonClass.toJson(object);
    }
}
