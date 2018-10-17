package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;

public class TweetList extends ArrayList<Tweet> {
    public ArrayList<Tweet> getTweets(){
        return this;
    }

    public boolean hasTweet(Tweet tweet){
        return this.contains(tweet);
    }

    public boolean addTweet(Tweet tweet){
        if (hasTweet(tweet)) {
            throw new IllegalArgumentException("Duplicate tweet: " + tweet.toString() + " cannot be added");
        } else {
            return this.add(tweet);
        }
    }

    public Tweet getTweet(int index) {
        return this.get(index);
    }

    public boolean deleteTweet(Tweet tweet){
        return this.remove(tweet);
    }

    public int getCount(){
        return this.size();
    }
}
