package ca.ualberta.cs.lonelytwitter;

import java.util.Date;


/**
 * Abstract class denoting a LonelyTwitter tweet.
 * <p>
 * Note: I absorbed the interface {@code Tweetable} into {@code Tweet} as It seemed irrelevant.
 */
public abstract class Tweet {
    private static final String TAG = "Tweet";
    private static final Integer MAX_CHARS = 140;
    private Date date;
    private String message;

    /**
     * Construct a {@code Tweet} with a default message of an empty string ``""``.
     * <p>
     * To set the message of a {@code Tweet} use the {@code setMessage} method.
     */
    Tweet() {
        this.date = new Date();
        this.message = "";
    }

    /**
     * Return the message of the {@code Tweet}.
     *
     * @return {@code String} the message of the {@code Tweet}.
     */
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

    /**
     * Returns the date the {@code Tweet} was created.
     *
     * @return {@code Date} The date the tweet was created.
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Abstract Getter-like method denoting whether a {@code Tweet} is important or not.
     *
     * @return {@code Boolean} indicating whether the Tweet is important or not.
     */
    public abstract Boolean isImportant();
}
