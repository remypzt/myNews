package myNews.view.activitiesAndFragment;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import myNews.myNews.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
	
	@Rule public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
	
	@Test
	public void mainActivityTest() {
		mActivityTestRule.getActivity()
		//.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
		;
		
		ViewInteraction overflowMenuButton = onView(allOf(withContentDescription("More options"), isDisplayed()));
		overflowMenuButton.perform(click());
		
		ViewInteraction appCompatTextView = onView(allOf(withId(R.id.title), withText("Notification"), childAtPosition(childAtPosition(withId(R.id.content), 0), 0), isDisplayed()));
		appCompatTextView.perform(click());
		
		ViewInteraction viewGroup = onView(allOf(withId(R.id.parentConstraintLayoutOfNotifications), childAtPosition(allOf(withId(R.id.constraintLayout), childAtPosition(withId(android.R.id.content), 0)), 1), isDisplayed()));
		viewGroup.check(matches(isDisplayed()));
	}
	
	private static Matcher<View> childAtPosition(final Matcher<View> parentMatcher,
	                                             final int position) {
		
		return new TypeSafeMatcher<View>() {
			@Override
			public void describeTo(Description description) {
				description.appendText("Child at position " + position + " in parent ");
				parentMatcher.describeTo(description);
			}
			
			@Override
			public boolean matchesSafely(View view) {
				ViewParent parent = view.getParent();
				return parent instanceof ViewGroup && parentMatcher.matches(parent) && view.equals(((ViewGroup) parent).getChildAt(position));
			}
		};
	}
	
}
