package it.UtilsApi.factory.impl;

import it.UtilsApi.factory.ObjectFactoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Service
@Slf4j
public class ObjectFactoryServiceImpl implements ObjectFactoryService {

    /**
     * Creates an object of a specified class using the provided parameters.
     *
     * @param params a map containing field names as keys and values to be assigned as values
     * @param clazz the class of the type of object to be created
     * @return a new instance of the specified class with field values set based on the provided parameters,
     *         or null if an error occurs during object creation
     */
   public <T> T createObject(Map<String, Object> params, Class<T> clazz) {
        try {
            T object = clazz.getDeclaredConstructor().newInstance();
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                try {
                    Field field = clazz.getDeclaredField(key);
                    field.setAccessible(true);
                    setFieldValue(object, field, value);
                } catch (NoSuchFieldException e) {
                    log.error("Il campo '{}' non è presente nella classe {}", key, clazz.getName());
                } catch (Exception e) {
                    log.error("Errore durante l'impostazione del campo '{}': {}", key, e.getMessage());
                }
            }
            return object;
        } catch (Exception e) {
            log.error("Errore durante la creazione dell'oggetto: {}", e.getMessage());
            return null;
        }
    }

    private void setFieldValue(Object object, Field field, Object value) throws Exception {
        if (field.getType().equals(Date.class)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dateValue = dateFormat.parse((String) value);
            field.set(object, dateValue);
        } else if (field.getType().equals(Integer.class) || field.getType().equals(int.class)) {
            try {
                Integer intValue = Integer.valueOf((String) value);
                field.set(object, intValue);
            } catch (NumberFormatException e) {
                log.error("Valore non valido per il campo '{}': {}", field.getName(), value);
            }
        } else {
            field.set(object, value);
        }
    }
}