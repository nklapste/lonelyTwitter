package ca.ualberta.cs.lonelytwitter;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class LonelyTwitterActivityTest {
    @Rule
    public ActivityTestRule<LonelyTwitterActivity> activityActivityTestRule = new ActivityTestRule<LonelyTwitterActivity>(LonelyTwitterActivity.class);
    private LonelyTwitterActivity mLonelyTwitterActivity;

    @Before
    public void init(){
        mLonelyTwitterActivity = activityActivityTestRule.getActivity();
    }
}