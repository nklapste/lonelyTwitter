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
    public void getTweet(){
        Tweet testTweet = getTestTweet();
        mTweetList.addTweet(testTweet);
        Tweet getTweet = mTweetList.get(0);
        assertEquals(testTweet.getDate(), getTweet.getDate());
    }

    @Test
    public void deleteTweet(){
        Tweet testTweet = getTestTweet();
        mTweetList.addTweet(testTweet);
        assertTrue(mTweetList.deleteTweet(testTweet));
        assertTrue(mTweetList.isEmpty());
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