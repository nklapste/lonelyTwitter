package ca.ualberta.cs.lonelytwitter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * RecyclerView adapter for integrating a {@code FeelQueue}.
 */
class TweetAdapter extends ArrayAdapter<Tweet> {
    private static final String TAG = "TweetAdapter";

    TweetAdapter(Context context, ArrayList<Tweet> tweets) {
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

        // Lookup view for data population

        TextView tweetMessage = (TextView) convertView.findViewById(R.id.tweet_message);

        // Populate the data into the template view using the data object
        tweetMessage.setText(tweet.getMessage());
        // Return the completed view to render on screen

        return convertView;

    }
}