package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Abstract class denoting a lonelytwitter tweet.
 * <p>
 * Note: I absorbed the interface Tweetable into Tweet as It seemed irrelevant.
 */
public abstract class Tweet {
    private static final String TAG = "Tweet";
    private static final Integer MAX_CHARS = 140;
    private Date date;
    private String message;

    Tweet() {
        this.date = new Date();
        this.message = "";
    }

    public String getMessage() {
        return this.message;
    }

    /**
     * Set a Tweets message.
     *
     * @param message {@code String} the message to be set to the {@code Tweet}.
     * @throws TweetTooLongException if the Tweet's message is over {@code MAX_CHARS}
     */
    public void setMessage(String message) throws TweetTooLongException {
        if (message.length() <= MAX_CHARS) {
            this.message = message;
        } else {
            throw new TweetTooLongException();
        }
    }

    public Date getDate() {
        return this.date;
    }

    //No method body implemented! We leave that up to the subclasses (they MUST implement it)
    public abstract Boolean isImportant();
}
