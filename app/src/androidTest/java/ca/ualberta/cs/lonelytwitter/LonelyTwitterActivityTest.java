package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;

import junit.framework.TestCase;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by wz on 14/09/15.
 */
public class LonelyTwitterActivityTest extends ActivityTestRule<LonelyTwitterActivity> {

    public LonelyTwitterActivityTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }

    public void testStart() throws Exception {
        Activity activity = getActivity();
    }

    @Test
    public void testFooSuccess() {
        assertEquals("1","1");
    }

    @Test
    public void testFooFail() {
        assertEquals("1","2");
    }
}