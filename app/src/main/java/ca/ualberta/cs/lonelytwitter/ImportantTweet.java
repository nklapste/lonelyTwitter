package ca.ualberta.cs.lonelytwitter;


/**
 * Subclass of Tweet
 * <p>
 * Provides ``important`` tweet behavior.
 *
 * @see Tweet
 */
public class ImportantTweet extends Tweet {
    ImportantTweet() {
        super();
    }

    @Override
    public Boolean isImportant() {
        return true;
    }
}
