package it.UtilsApi.factory;

import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


@Service
public class ObjectFactoryService {

    /**
     * Crea un oggetto di una classe specificata utilizzando i parametri forniti.
     *
     * @param params una mappa contenente i nomi dei campi come chiavi e i valori da assegnare come valori
     * @param clazz la classe del tipo di oggetto che si desidera creare
     * @return un nuovo oggetto istanziato della classe specificata con i valori dei campi impostati in base ai parametri forniti
     */
    public Object createObject(Map<String, Object> params, Class<?> clazz) {
        try {
            Object object = clazz.getDeclaredConstructor().newInstance();
            if(!params.isEmpty()){
                params.forEach((key, value) -> {
                    try {
                        Field field = clazz.getDeclaredField(key);
                        field.setAccessible(true);
                        if (field.getType().equals(Date.class)) {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            Date dateValue = dateFormat.parse((String) value);
                            field.set(object, dateValue);
                        } else if (field.getType().equals(Integer.class)) {
                            try {
                                Integer intValue = Integer.valueOf((String) value);
                                field.set(object, intValue);
                            } catch (NumberFormatException e) {
                                System.err.println("Valore non valido per il campo '" + key + "': " + value);
                            }
                        } else {
                            field.set(object, value);
                        }
                    } catch (NoSuchFieldException e) {
                        System.err.println("Il campo '" + key + "' non è presente nella classe " + clazz.getName());
                    } catch (Exception e) {
                        System.err.println("Errore durante l'impostazione del campo '" + key + "': " + e.getMessage());
                    }
                });

                return object;
            }else{
                return null;
            }

        } catch (Exception e) {
            System.err.println("Errore durante la creazione dell'oggetto: " + e.getMessage());
            return null;
        }
    }

}
