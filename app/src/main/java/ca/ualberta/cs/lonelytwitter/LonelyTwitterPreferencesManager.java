package ca.ualberta.cs.lonelytwitter;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * Helper Class for accessing the  Android's SharedPreferences for use in LonelyTwitter.
 *
 * @see TweetSerializer
 * Use {@code Gson} to serialize/deserialize.
 */
public class LonelyTwitterPreferencesManager {
    private static final String TAG = "LonelyTwitterPreferencesManager";

    private static final String TWEET_LIST_PREF_NAME = "mTweetList";
    private static final String TWEET_LIST_PREF_JSON_KEY = "mTweetListJson";

    /**
     * Save a TweetList using Android's SharedPreferences.
     *
     * @param context   {@code Context}
     * @param tweetList {@code TweetList}
     */
    public static void saveSharedPreferencesTweetList(Context context, TweetList tweetList) {
        SharedPreferences mPrefs = context.getSharedPreferences(TWEET_LIST_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();

        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(Tweet.class, new TweetSerializer());
        Gson gson = gb.create();

        String json = gson.toJson(tweetList);
        prefsEditor.putString(TWEET_LIST_PREF_JSON_KEY, json);
        prefsEditor.apply();
    }

    /**
     * Load the TweetList using Android's SharedPreferences.
     *
     * @param context {@code Context}
     */
    public static TweetList loadSharedPreferencesTweetList(Context context) {
        TweetList tweetList;
        SharedPreferences mPrefs = context.getSharedPreferences(TWEET_LIST_PREF_NAME, Context.MODE_PRIVATE);

        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(Tweet.class, new TweetSerializer());
        Gson gson = gb.create();

        String json = mPrefs.getString(TWEET_LIST_PREF_JSON_KEY, "");
        if (json.isEmpty()) {
            tweetList = new TweetList();
        } else {
            tweetList = gson.fromJson(json, TweetList.class);
        }
        return tweetList;
    }
}