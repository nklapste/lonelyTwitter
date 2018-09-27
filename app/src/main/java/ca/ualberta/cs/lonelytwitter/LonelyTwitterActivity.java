package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class LonelyTwitterActivity extends Activity {
	private ListView oldTweetsList;
    private TweetAdapter mTweetAdapter;
	private TweetList mTweetList;


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final EditText bodyText = (EditText) findViewById(R.id.body);
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);

		final Button saveButton = (Button) findViewById(R.id.save);
		saveButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String text = bodyText.getText().toString();
                NormalTweet tweet = new NormalTweet(text);
                mTweetList.add(tweet);
                mTweetAdapter.notifyDataSetChanged();
				LonelyTwitterPreferencesManager.saveSharedPreferencesTweetList(
						getApplicationContext(),
						mTweetList
				);
			}
		});

		final Button clearButton = (Button) findViewById(R.id.clear);
		clearButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
                mTweetList.clear();
                mTweetAdapter.notifyDataSetChanged();
				LonelyTwitterPreferencesManager.saveSharedPreferencesTweetList(
						getApplicationContext(),
						mTweetList
				);
			}
		});
	}

    /**
     * Save the MainActivity's {@code mTweetList} on closing.
     */
    @Override
    protected void onDestroy() {
        LonelyTwitterPreferencesManager.saveSharedPreferencesTweetList(
        		getApplicationContext(),
				mTweetList
		);
        super.onDestroy();
    }

	/**
	 * On Activity start load the TweetList from the PreferencesManager and set the
	 * {@code mTweetAdapter} and adapter on {@code oldTweetsList}.
	 */
    @Override
	protected void onStart() {
		super.onStart();
        mTweetList = LonelyTwitterPreferencesManager.loadSharedPreferencesTweetList(
		        getApplicationContext()
        );
		mTweetAdapter = new TweetAdapter(this, mTweetList);
		oldTweetsList.setAdapter(mTweetAdapter);
	}
}