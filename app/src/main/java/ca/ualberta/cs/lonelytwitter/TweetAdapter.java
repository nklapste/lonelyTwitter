package ca.ualberta.cs.lonelytwitter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Tweet adapter for integrating a {@code TweetList}.
 */
class TweetAdapter extends ArrayAdapter<Tweet> {
    private static final String TAG = "TweetAdapter";

    TweetAdapter(Context context, TweetList tweets) {
        super(context, 0, tweets);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Tweet tweet = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // set the message of the tweet
        TextView tweetMessage = (TextView) convertView.findViewById(R.id.tweet_message);
        tweetMessage.setText(tweet.getMessage());
        return convertView;
    }
}