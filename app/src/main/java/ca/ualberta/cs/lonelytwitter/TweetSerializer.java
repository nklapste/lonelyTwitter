package ca.ualberta.cs.lonelytwitter;

import com.google.gson.*;
import java.lang.reflect.Type;


/**
 * Add-in to allow JSON Serializing of subclasses of Tweet.
 */
public class TweetSerializer implements JsonSerializer<Tweet>, JsonDeserializer<Tweet> {
    private static final String TAG = "TweetSerializer";

    @Override
    public JsonElement serialize(Tweet src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.add("type", new JsonPrimitive(src.getClass().getSimpleName()));
        result.add("properties", context.serialize(src, src.getClass()));

        return result;
    }

    @Override
    public Tweet deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();
        JsonElement element = jsonObject.get("properties");

        try {
            return context.deserialize(element, Class.forName("ca.ualberta.cs.lonelytwitter." + type));
        } catch (ClassNotFoundException cnfe) {
            throw new JsonParseException("Unknown element type: " + type, cnfe);
        }
    }
}
