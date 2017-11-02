package m2t.com.tashilatappprototype.common.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import m2t.com.tashilatappprototype.common.pojo.Categories;
import m2t.com.tashilatappprototype.common.pojo.Item;

/**
 * Created by laanaya on 11/1/17.
 */

public class CustomJsonDeserializer implements JsonDeserializer<Categories> {
    @Override
    public Categories deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {


        Categories categories = new Categories();

        JsonObject jsonObject = json.getAsJsonObject();
        if (jsonObject.get("Item").isJsonArray()) {

            JsonArray jsonArray = jsonObject.get("Item").getAsJsonArray();
            categories.setItem(new Item(jsonArray.get(0).getAsJsonObject().get("ID").getAsString(),
                    jsonArray.get(0).getAsJsonObject().get("Name").getAsString()));
        } else {

            categories.setItem(new Item(jsonObject.get("Item").getAsJsonObject().get("ID").getAsString(),
                    jsonObject.get("Item").getAsJsonObject().get("Name").getAsString()));
        }

        return categories;
    }
}
