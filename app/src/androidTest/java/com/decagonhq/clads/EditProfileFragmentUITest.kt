package com.decagonhq.clads

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.decagonhq.clads.fragments.profilemanagement.EditProfileFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class EditProfileFragmentUITest {

    @Before
    fun setUp() {
        val navController = Mockito.mock(NavController::class.java)
        val scenario: FragmentScenario<EditProfileFragment> = launchFragmentInContainer(themeResId = R.style.Theme_Clads) {
            EditProfileFragment().also { fragment ->
                fragment.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                    if (viewLifecycleOwner != null) {
                        Navigation.setViewNavController(fragment.requireView(), navController)
                    }
                }
            }
        }
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun is_viewpager_displayed() {
        onView(withId(R.id.edit_profile_viewpager2)).check(matches(isDisplayed()))
    }

    @Test
    fun is_tablayout_displayed() {
        onView(withId(R.id.fragment_edit_profile_tablayout)).check(matches(isDisplayed()))
    }

    @Test
    fun is_toolbar_displayed() {
        onView(withId(R.id.edit_profile_toolbar)).check(matches(isDisplayed()))
    }
}
