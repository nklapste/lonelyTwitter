package ca.ualberta.cs.lonelytwitter;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.EditText;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

/**
 * Created by wz on 14/09/15.
 */
@RunWith(AndroidJUnit4.class)
public class LonelyTwitterActivityTest extends ActivityTestRule<LonelyTwitterActivity> {

    public LonelyTwitterActivityTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }

    private Solo solo;

    @Before
    public void Setup(){
        super.launchActivity(new Intent());
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @After
    public void tearDown(){
        solo.finishOpenedActivities();
    }

    @Test
    public void testTweet(){
        solo.assertCurrentActivity("wrong activity", "LonelyTwitterActivity");
    }

    @Test
    public void testAddTweet(){
        solo.enterText((EditText) solo.getView(R.id.body), "new thing");
        solo.clickOnButton("Save");
        solo.clearEditText((EditText) solo.getView(R.id.body));
        assertTrue(solo.waitForText("new thing"));
    }

    @Test
    public void testEditTweet() {
        solo.clickOnButton("Clear");
        solo.assertCurrentActivity("Wrong activity", LonelyTwitterActivity.class);
        EditText editText = (EditText) solo.getView(R.id.body);
        solo.enterText((EditText) solo.getView(R.id.body), "new thing");
        solo.clickOnButton("Save");
        solo.clearEditText(editText);
        solo.clickInList(1);
        solo.assertCurrentActivity("Wrong activity", EditTweetActivity.class);
        assertTrue(solo.searchText("new thing"));
    }
}