package ca.ualberta.cs.lonelytwitter;

/**
 * A Exception denoting that a {@code Tweet}s message is too long.
 */
public class TweetTooLongException extends Exception {
    TweetTooLongException() {
        super("The message is too long! Please keep your tweets within 140 characters.");
    }
}
