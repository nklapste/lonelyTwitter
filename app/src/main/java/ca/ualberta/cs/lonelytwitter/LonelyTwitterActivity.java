package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class LonelyTwitterActivity extends Activity {
    private static final String TAG = "LonelyTwitterActivity";

    private EditText bodyText;
    private ListView oldTweetsList;
    private ArrayList<Tweet> tweetList = new ArrayList<Tweet>();
    private ArrayAdapter<Tweet> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        bodyText = (EditText) findViewById(R.id.body);
        Button saveButton = (Button) findViewById(R.id.save);
        Button searchButton = (Button) findViewById(R.id.search);
        oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);

        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);
                String text = bodyText.getText().toString();
                Tweet newTweet = new NormalTweet(text);
                tweetList.add(newTweet);
                adapter.notifyDataSetChanged();
                new ElasticsearchTweetController.AddTweetsTask().execute(tweetList.toArray(new NormalTweet[tweetList.size()]));
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tweetList.clear();
                try {
                    tweetList = new ElasticsearchTweetController.GetTweetsTask().execute(bodyText.getText().toString()).get();
                } catch (ExecutionException e) {
                    Log.e(TAG, "Failed to run search", e);
                } catch (InterruptedException e) {
                    Log.e(TAG, "Failed to run search", e);
                }
                setResult(RESULT_OK);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            tweetList = new ElasticsearchTweetController.GetTweetsTask().execute("").get();
        } catch (ExecutionException e) {
            Log.e(TAG, "Failed to get initial tweetList!", e);
        } catch (InterruptedException e) {
            Log.e(TAG, "Failed to get initial tweetList!", e);
        }
        adapter = new ArrayAdapter<Tweet>(this, R.layout.list_item, tweetList);
        oldTweetsList.setAdapter(adapter);
    }
}