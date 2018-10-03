package ca.ualberta.cs.lonelytwitter;

/**
 * Subclass of Tweet
 * <p>
 * Provides ``normal`` tweet behavior.
 *
 * @see Tweet
 */
public class NormalTweet extends Tweet {
    NormalTweet() {
        super();
    }

    NormalTweet(String message) {
        super(message);
    }

    @Override
    public Boolean isImportant() {
        return false;
    }
}
