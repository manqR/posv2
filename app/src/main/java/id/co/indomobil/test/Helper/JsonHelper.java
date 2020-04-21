package id.co.indomobil.test.Helper;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class JsonHelper {

    public static <T> T deserializeJsonToObjet(T TheClass,String jsonText){
        T obj = null;
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            obj =  mapper.readValue(jsonText, new TypeReference<T>() {});
            System.out.println(mapper.writeValueAsString(obj));
        } catch (JsonGenerationException e)
        {
            e.printStackTrace();
        } catch (JsonMappingException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return obj;
    }

    public static <T> ArrayList<T> deserializeJsonToObjectArrayList(Class<T> TheClass, String jsonText){
        ArrayList<T> obj = new ArrayList<T>();

        Type targetClassType = new TypeToken<ArrayList<T>>() { }.getType();
        obj = new Gson().fromJson(jsonText, targetClassType);

        return obj;
    }


    public static <T> String serializeObjectToJson(T TheObject){
        String jsonText = "{}";
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            jsonText =  mapper.writeValueAsString(TheObject);

            System.out.println(jsonText);
        } catch (JsonGenerationException e)
        {
            e.printStackTrace();
        } catch (JsonMappingException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return jsonText;
    }
}
