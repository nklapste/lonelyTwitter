package ca.ualberta.cs.lonelytwitter;

import android.os.AsyncTask;
import android.util.Log;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.searchbox.client.JestResult;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;

/**
 * Created by romansky on 10/20/16.
 */
class ElasticsearchTweetController {
    private static final String TAG = "ElasticsearchController";
    private static JestDroidClient client;

    static void verifySettings() {
        if (client == null) {
            DroidClientConfig.Builder builder = new DroidClientConfig.Builder("http://cmput301.softwareprocess.es:8080");
            DroidClientConfig config = builder.build();

            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();
        }
    }

    public static class GetTweetsTask extends AsyncTask<String, Void, ArrayList<Tweet>> {
        @Override
        protected ArrayList<Tweet> doInBackground(String... search_parameters) {
            verifySettings();
            Log.i(TAG, "Elastic search parameters: " + Arrays.toString(search_parameters));
            ArrayList<Tweet> tweets = new ArrayList<>();
            Search search = new Search.Builder(search_parameters[0])
                    .addIndex("nklapste-wednesday")
                    .addType("tweet")
                    .build();
            try {
                JestResult result = client.execute(search);
                if (result.isSucceeded()) {
                    List<NormalTweet> tweetList;
                    tweetList = result.getSourceAsObjectList(NormalTweet.class);
                    Log.d(TAG, "obtained tweets: " + tweetList.toString());
                    tweets.addAll(tweetList);
                }
            } catch (Exception e) {
                Log.e(TAG, "Something went wrong when we tried to communicate with the elasticsearch server!", e);
            }
            return tweets;
        }
    }

    // TODO we need a function which adds tweets to elastic search
    public static class AddTweetsTask extends AsyncTask<NormalTweet, Void, Void> {

        @Override
        protected Void doInBackground(NormalTweet... tweets) {
            verifySettings();
            for (NormalTweet tweet : tweets) {
                Index index = new Index.Builder(tweet).index("nklapste-wednesday").type("tweet").build();
                try {
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()) {
                        tweet.setTweetID(result.getId());
                    }
                } catch (Exception e) {
                    Log.e(TAG, "The application failed to build and send the tweets", e);
                }
            }
            return null;
        }
    }
}