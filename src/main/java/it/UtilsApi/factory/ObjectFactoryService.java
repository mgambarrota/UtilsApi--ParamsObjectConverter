package it.UtilsApi.factory;

import java.util.Map;

public interface ObjectFactoryService {
    public <T> T createObject(Map<String, Object> params, Class<T> clazz);
}
