package com.dbs.uitestsupport

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText

class AlertDialogRobot {

    fun seesMessageOrTitle(str: String) {
        onView(withText(str)).check(matches(isDisplayed()))
    }

    fun seesOkButton(ok: String) {
        onView(withText(ok)).check(matches(isDisplayed()))
    }
}