package ca.ualberta.cs.lonelytwitter;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Helper Class for accessing the  Android's SharedPreferences for use in LonelyTwitter.
 *
 * Use {@code Gson} to serialize/deserialize TODO: DOC
 */
public class LonelyTwitterPreferencesManager {
    private static final String TAG = "FeelsBookPreferencesManager";

    private static final String TWEET_LIST_PREF_NAME = "mTweetList";
    private static final String TWEET_LIST_PREF_JSON_KEY = "mTweetListJson";

    /**
     * Save a FeelsList using Android's SharedPreferences.
     *
     * @param context  {@code Context}
     * @param tweetList {@code ArrayList<Tweet>}
     */
    public static void saveSharedPreferencesTweetList(Context context, ArrayList<Tweet> tweetList) {
        SharedPreferences mPrefs = context.getSharedPreferences(TWEET_LIST_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(tweetList);
        prefsEditor.putString(TWEET_LIST_PREF_JSON_KEY, json);
        prefsEditor.apply();
    }

    /**
     * Load the FeelsList using Android's SharedPreferences.
     *
     * @param context {@code Context}
     */
    public static ArrayList<Tweet> loadSharedPreferencesFeelList(Context context) {
        ArrayList<Tweet> tweetList;
        SharedPreferences mPrefs = context.getSharedPreferences(TWEET_LIST_PREF_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString(TWEET_LIST_PREF_JSON_KEY, "");
        if (json.isEmpty()) {
            tweetList = new ArrayList<>();
        } else {
            Type type = new TypeToken<ArrayList<Tweet>>() {
            }.getType();
            tweetList = gson.fromJson(json, type);
        }
        return tweetList;
    }
}
