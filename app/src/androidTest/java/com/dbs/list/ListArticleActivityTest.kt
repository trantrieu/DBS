package com.dbs.list

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.dbs.R
import com.dbs.uitestsupport.AlertDialogRobot
import com.dbs.uitestsupport.MockWebServerRule
import com.dbs.uitestsupport.RecyclerViewRobot
import com.dbs.uitestsupport.WebServerRobot
import org.junit.Rule
import org.junit.Test

class ListArticleActivityTest {

    @get:Rule
    var mockWebServerRule = MockWebServerRule()
    @get:Rule
    var intentTestRule = IntentsTestRule(ListArticleActivity::class.java, false, false)

    private val webServerRobot = WebServerRobot(mockWebServerRule.getMockWebServer())
    private val alertDialogRobot = AlertDialogRobot()

    @Test
    fun onLoad_givenArticleList_seesLoadingSpinner() {

        intentTestRule.launchActivity(null)

        alertDialogRobot.seesMessageOrTitle("Loading")
    }

    @Test
    fun onLoad_givenArticleList_seesArticleList() {

        webServerRobot.mockArticleListSuccessful()
        intentTestRule.launchActivity(null)

        onView(RecyclerViewRobot.getItemViewAtPosition(R.id.recycler_view_items, 0, R.id.article_title))
            .check(matches(withText("title1")))
        onView(RecyclerViewRobot.getItemViewAtPosition(R.id.recycler_view_items, 0, R.id.article_last_update))
            .check(matches(withText("19-01-1970")))
        onView(RecyclerViewRobot.getItemViewAtPosition(R.id.recycler_view_items, 0, R.id.article_description))
            .check(matches(withText("desc1")))
        onView(RecyclerViewRobot.getItemViewAtPosition(R.id.recycler_view_items, 1, R.id.article_title))
            .check(matches(withText("title3")))
        onView(RecyclerViewRobot.getItemViewAtPosition(R.id.recycler_view_items, 1, R.id.article_last_update))
            .check(matches(withText("19-01-1970")))
        onView(RecyclerViewRobot.getItemViewAtPosition(R.id.recycler_view_items, 1, R.id.article_description))
            .check(matches(withText("desc3")))
        onView(RecyclerViewRobot.getItemViewAtPosition(R.id.recycler_view_items, 2, R.id.article_title))
            .check(matches(withText("title2")))
        onView(RecyclerViewRobot.getItemViewAtPosition(R.id.recycler_view_items, 2, R.id.article_last_update))
            .check(matches(withText("19-01-1970")))
        onView(RecyclerViewRobot.getItemViewAtPosition(R.id.recycler_view_items, 2, R.id.article_description))
            .check(matches(withText("desc2")))
        onView(RecyclerViewRobot.getItemViewAtPosition(R.id.recycler_view_items, 3, R.id.article_title))
            .check(matches(withText("title4")))
        onView(RecyclerViewRobot.getItemViewAtPosition(R.id.recycler_view_items, 3, R.id.article_last_update))
            .check(matches(withText("19-01-1970")))
        onView(RecyclerViewRobot.getItemViewAtPosition(R.id.recycler_view_items, 3, R.id.article_description))
            .check(matches(withText("desc4")))
    }

    @Test
    fun onLoad_givenArticleFailure_seesErrorPopup() {

        webServerRobot.mockArticleFailure(500)
        intentTestRule.launchActivity(null)

        alertDialogRobot.seesMessageOrTitle("HTTP 500 Server Error")
        alertDialogRobot.seesOkButton("OK")
    }

}