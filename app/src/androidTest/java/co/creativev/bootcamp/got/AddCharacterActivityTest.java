package co.creativev.bootcamp.got;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

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

    @Test
    public void testAddingCharacterWithValidDataAddsTheCharacter() throws Exception {

        Resources resources = InstrumentationRegistry.getTargetContext().getResources();
        Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                resources.getResourcePackageName(R.mipmap.ic_launcher) + '/' +
                resources.getResourceTypeName(R.mipmap.ic_launcher) + '/' +
                resources.getResourceEntryName(R.mipmap.ic_launcher));

        Intent resultData = new Intent();
        resultData.setData(imageUri);
        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(
                Activity.RESULT_OK, resultData);

        Intents.init();
        Matcher<Intent> expectedIntent = allOf(hasAction(Intent.ACTION_GET_CONTENT));
        intending(expectedIntent).respondWith(result);
        onView(withId(R.id.image_character)).perform(click());
        Intents.release();

        onView(withId(R.id.text_character_name)).perform(typeText("Tony Stark"), closeSoftKeyboard());
        onView(withId(R.id.radio_stark)).perform(click());

        onView(withId(R.id.button_save)).perform(click());
        onView(withText("Inserted new character")).check(matches(isDisplayed()));
    }
}