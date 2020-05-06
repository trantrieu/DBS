package com.dbs.detail

import android.app.Activity.RESULT_OK
import android.app.Instrumentation
import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.toPackage
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.dbs.data.article.detail.Detail
import com.dbs.data.article.list.Article
import com.dbs.detail.DetailActivity.Companion.DETAIL_EXTRA
import com.dbs.list.ListArticleActivity
import org.junit.Rule
import org.junit.Test
import com.dbs.R
import com.dbs.edit.EditActivity
import org.junit.After
import org.junit.Before

internal class DetailActivityTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(DetailActivity::class.java, false, false)

    @Before
    fun setup() {
        Intents.init()
    }

    @After
    fun clear() {
        Intents.release()
    }

    @Test
    fun onLoad_seesData() {
        launchActivity()
        onView(withText("title1")).check(matches(isDisplayed()))
        onView(withId(R.id.article_long_text)).check(matches(withText("long text")))
    }

    @Test
    fun onLoad_clicksEdit_seesEditScreen() {
        launchActivity()
        onView(withId(R.id.action_edit)).perform(click())
        Intents.intended(IntentMatchers.hasComponent(EditActivity::class.java.name))
    }

    @Test
    fun onActivityResult_receivedEditDetail_seesData() {
        launchActivity()
        mockEditDetail()
        onView(withId(R.id.action_edit)).perform(click())
        onView(withText("title1")).check(matches(isDisplayed()))
        onView(withId(R.id.article_long_text)).check(matches(withText("edit text")))
    }

    private fun mockEditDetail() {
        val resultData = Intent()
        resultData.putExtra(DETAIL_EXTRA, createDetail(createArticle(), "edit text"))
        val result = Instrumentation.ActivityResult(RESULT_OK, resultData)
        intending(hasComponent("com.dbs.edit.EditActivity")).respondWith(result)
    }

    private fun launchActivity() {
        val detail = createDetail(createArticle())
        val intent = Intent()
        intent.putExtra(DETAIL_EXTRA, detail)
        activityTestRule.launchActivity(intent)
    }

    private fun createArticle() = Article(1, "title1", 1586404611, "short_desc1", "avatar_url1")

    private fun createDetail(article: Article, longText: String = "long text") = Detail(article.id, longText, article)

}