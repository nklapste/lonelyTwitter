package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class EditTweetActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tweet);
        Intent intent = getIntent();
        String tweetMessage = intent.getStringExtra("message");
        String tweetDate = intent.getStringExtra("date");
        TextView messageTextView = findViewById(R.id.editTweetMessage);
        TextView dateTextView = findViewById(R.id.editTweetDate);
        messageTextView.setText(tweetMessage);
        dateTextView.setText(tweetDate);
    }
}
