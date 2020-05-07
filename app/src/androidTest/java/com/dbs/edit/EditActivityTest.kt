package com.dbs.edit

import android.app.Instrumentation
import android.content.Intent
import androidx.core.app.TaskStackBuilder
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.dbs.R
import com.dbs.data.article.detail.Detail
import com.dbs.data.article.list.Article
import com.dbs.detail.DetailActivity.Companion.DETAIL_EXTRA
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class EditActivityTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(EditActivity::class.java, false, false)

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
        onView(withId(R.id.edit_text_detail)).check(matches(withText("long text")))
    }

    private fun launchActivity() {
        val detail = createDetail(createArticle())
        val intent = Intent()
        intent.putExtra(DETAIL_EXTRA, detail)
        activityTestRule.launchActivity(intent)
    }

    private fun createArticle() = Article(1, "title1", 1586404611, "short_desc1", "avatar_url1")

    private fun createDetail(article: Article) = Detail(article.id, "long text", article)
}