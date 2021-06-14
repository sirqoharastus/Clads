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
import com.decagonhq.clads.fragments.profilemanagement.AccountFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class EditProfileAccountFragmentUiTest {

    @Before
    fun setUp() {
        val navController = Mockito.mock(NavController::class.java)
        val scenario: FragmentScenario<AccountFragment> = launchFragmentInContainer(themeResId = R.style.Theme_Clads) {
            AccountFragment().also { fragment ->
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
    fun is_first_name_textview_displayed() {
        onView(withId(R.id.edit_profile_first_name_textview)).check(matches(isDisplayed()))
    }

    @Test
    fun is_last_name_text_view_displayed() {
        onView(withId(R.id.edit_profile_last_name_textview)).check(matches(isDisplayed()))
    }

    @Test
    fun is_other_name_text_view_displayed() {
        onView(withId(R.id.edit_profile_other_name_textview)).check(matches(isDisplayed()))
    }

    @Test
    fun is_gender_text_view_displayed() {
        onView(withId(R.id.edit_profile_gender_textview)).check(matches(isDisplayed()))
    }

    @Test
    fun is_work_address_textview_displayed() {
        onView(withId(R.id.edit_profile_work_address_textview)).check(matches(isDisplayed()))
    }

    @Test
    fun is_showroom_address_textview_displayed() {
        onView(withId(R.id.edit_profile_showroom_addrress_textview)).check(matches(isDisplayed()))
    }
}
