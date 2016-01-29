package co.creativev.bootcamp.got;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class AddCharacterActivityTest {
    @Rule
    public ActivityTestRule<AddCharacterActivity> activityTestRule = new ActivityTestRule<AddCharacterActivity>(AddCharacterActivity.class);

    @Test
    public void testAddingCharacterUnsuccessful() {
        onView(withId(R.id.text_character_name)).perform(typeText("Tony Stark"), closeSoftKeyboard());
        onView(withId(R.id.radio_stark)).perform(click());
        onView(withId(R.id.button_save)).perform(click());
        onView(withText("Image is not selected")).check(matches(isDisplayed()));
    }
}