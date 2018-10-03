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

    @Override
    public Boolean isImportant() {
        return false;
    }
}
