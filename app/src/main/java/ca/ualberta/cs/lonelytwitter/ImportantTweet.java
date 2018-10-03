package ca.ualberta.cs.lonelytwitter;


/**
 * Subclass of Tweet
 * <p>
 * Provides ``important`` tweet behavior.
 *
 * @see Tweet
 */
public class ImportantTweet extends Tweet {

    //Empty argument constructor with default values
    ImportantTweet() {
        //Call the parent constructor to: avoid duplication!
        super();
    }

    @Override
    public Boolean isImportant() {
        return true;
    }
}
