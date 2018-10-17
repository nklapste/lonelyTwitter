package ca.ualberta.cs.lonelytwitter;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TweetListTest {
    private TweetList mTweetList;
    private Tweet getTestTweet(){
        return new NormalTweet("foobar");
    }

    @Before
    public void setup() {
        mTweetList = new TweetList();
    }

    @Test
    public void getTweets() {
        ArrayList tweetList = mTweetList.getTweets();
        assertNotNull(tweetList);
    }

    @Test
    public void addTweet() {
        Tweet testTweet = getTestTweet();
        mTweetList.add(testTweet);
        assertNotNull(mTweetList);
        assertTrue(mTweetList.hasTweet(testTweet));
    }

    @Test
    public void getCount() {
        assertEquals(0, mTweetList.getCount());
    }
    @Test
    public void hasTweetMissingTweet(){
        Tweet testTweet = getTestTweet();
        assertFalse(mTweetList.hasTweet(testTweet));
    }

    @Test
    public void hasTweetExistingTweet(){
        Tweet testTweet = getTestTweet();
        mTweetList.addTweet(testTweet);
        assertTrue(mTweetList.hasTweet(testTweet));
    }

}