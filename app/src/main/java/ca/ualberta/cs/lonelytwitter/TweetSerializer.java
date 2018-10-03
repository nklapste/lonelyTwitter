package ca.ualberta.cs.lonelytwitter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;


/**
 * Add-in to allow GSON/JSON Serializing of the abstract class Tweet and its subclasses.
 *
 * @see LonelyTwitterPreferencesManager uses this to properly (de)serialize {@code TweetList}s.
 */
class TweetSerializer implements JsonSerializer<Tweet>, JsonDeserializer<Tweet> {
    private static final String TAG = "TweetSerializer";

    /**
     * Serialize an {@code Tweet} and consider additional class attributes such as type and properties.
     *
     * @see Tweet
     * @see com.google.gson.JsonSerializer
     *
     * @param tweet {@code Tweet}
     * @param typeOfSrc {@code Type}
     * @param context {@code JsonSerializationContext}
     * @return {@code JsonElement}
     */
    @Override
    public JsonElement serialize(Tweet tweet, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.add("type", new JsonPrimitive(tweet.getClass().getSimpleName()));
        result.add("properties", context.serialize(tweet, tweet.getClass()));
        return result;
    }

    /**
     * Deserialize a JsonElement considering additional class attributes such as
     * type and properties.
     *
     * @see Tweet
     * @see com.google.gson.JsonDeserializer
     *
     * @param json {@code JsonElement}
     * @param typeOfT {@code Type}
     * @param context {@code JsonDeserializationContext}
     * @return {@code Tweet} or a respective subclass of {@code Tweet}.
     * @throws JsonParseException if the class specified by the deserializer does not exist or is unknown.
     */
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
