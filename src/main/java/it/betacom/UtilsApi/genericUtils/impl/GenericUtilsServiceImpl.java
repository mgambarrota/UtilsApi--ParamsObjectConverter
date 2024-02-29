package it.betacom.UtilsApi.genericUtils.impl;

import com.google.gson.Gson;
import it.betacom.UtilsApi.genericUtils.GenericUtilsService;
import org.springframework.stereotype.Service;

@Service
public class GenericUtilsServiceImpl implements GenericUtilsService {
    private final Gson gsonClass = new Gson();
    @Override
    public String convertToJson(Object object) {
        return gsonClass.toJson(object);
    }
}
