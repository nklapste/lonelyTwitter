package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class LonelyTwitterActivity extends Activity {
	private EditText bodyText;
	private ListView oldTweetsList;
    private TweetAdapter mTweetAdapter;

    /** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		bodyText = (EditText) findViewById(R.id.body);
		Button saveButton = (Button) findViewById(R.id.save);
		Button clearButton = (Button) findViewById(R.id.clear);
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);

		saveButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				setResult(RESULT_OK);
				String text = bodyText.getText().toString();
                Tweet tweet = new NormalTweet(text);
                mTweetList.add(tweet);
                mTweetAdapter.notifyDataSetChanged();
			}
		});

		clearButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
                mTweetList.clear();
                mTweetAdapter.notifyDataSetChanged();
			}
		});
	}
    /**
     * Save the MainActivity's {@code TweetList} on closing.
     */
    @Override
    protected void onDestroy() {
        LonelyTwitterPreferencesManager.saveSharedPreferencesTweetList(getApplicationContext(), mTweetList);
        super.onDestroy();
    }

    ArrayList<Tweet> mTweetList;
    @Override
	protected void onStart() {
		super.onStart();
        mTweetList = LonelyTwitterPreferencesManager.loadSharedPreferencesFeelList(
		        getApplicationContext()
        );
		mTweetAdapter = new TweetAdapter(this, mTweetList);
		oldTweetsList.setAdapter(mTweetAdapter);
	}
}