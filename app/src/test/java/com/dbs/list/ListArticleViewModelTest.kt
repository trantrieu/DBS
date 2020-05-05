package com.dbs.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dbs.article.ArticleListResult
import com.dbs.article.ArticleProvider
import com.dbs.config.SchedulerConfig
import com.dbs.data.article.list.Article
import com.dbs.testsupport.getOrAwaitValue
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.lang.Exception

@RunWith(MockitoJUnitRunner::class)
internal class ListArticleViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var articleProvider: ArticleProvider
    @Mock
    private lateinit var schedulerConfig: SchedulerConfig

    private lateinit var subject: ListArticleViewModel

    @Before
    fun setup() {
        `when`(schedulerConfig.getMainScheduler()).thenReturn(Schedulers.trampoline())
        subject = ListArticleViewModel(articleProvider, schedulerConfig, CompositeDisposable())
    }

    @Test
    fun fetchArticleList_givenArticleSuccess_checkArticleMutableLiveDataValue() {
        `when`(articleProvider.fetchListArticle()).thenReturn(Single.just(ArticleListResult.Success(createListArticle())))

        subject.fetchArticleList()

        val actual = subject.articleLiveData.getOrAwaitValue()
        assertEquals(2, actual.size)

        assertEquals(1, actual[0].id)
        assertEquals("title1", actual[0].title)
        assertEquals("short_desc1", actual[0].shortDescription)
        assertEquals("avatar_url1", actual[0].avatarUrl)
        assertEquals("19-01-1970", actual[0].lastUpdate)

        assertEquals(2, actual[1].id)
        assertEquals("title2", actual[1].title)
        assertEquals("short_desc2", actual[1].shortDescription)
        assertEquals("avatar_url2", actual[1].avatarUrl)
        assertEquals("19-01-1970", actual[1].lastUpdate)
    }

    @Test
    fun fetchArticleList_givenArticleFailure_checkErrorMutableLiveDataValue() {
        `when`(articleProvider.fetchListArticle()).thenReturn(Single.just(ArticleListResult.Failure("Exception")))

        subject.fetchArticleList()

        val actual = subject.errorErrorLiveData.getOrAwaitValue()

        assertEquals(actual.getContentIfNotHandled(), "Exception")
    }

    @Test
    fun fetchArticleList_givenExceptionOnProvider_checkErrorMutableLiveDataValue() {
        `when`(articleProvider.fetchListArticle()).thenReturn(Single.error(Exception("Exception")))

        subject.fetchArticleList()

        val actual = subject.errorErrorLiveData.getOrAwaitValue()

        assertEquals(actual.getContentIfNotHandled(), "Exception")
    }

    @Test
    fun fetchArticleList_givenEmptyExceptionOnProvider_checkErrorMutableLiveDataValue() {
        `when`(articleProvider.fetchListArticle()).thenReturn(Single.error(Exception()))

        subject.fetchArticleList()

        val actual = subject.errorErrorLiveData.getOrAwaitValue()

        assertEquals(actual.getContentIfNotHandled(), "Generic error")
    }

    @Test
    fun fetchArticleList_checkLoadingSpinnerLiveDataValue() {
        `when`(articleProvider.fetchListArticle()).thenReturn(Single.error(Exception()))

        subject.fetchArticleList()

        val actual = subject.loadingSpinnerLiveData.getOrAwaitValue()

        assertEquals(actual.getContentIfNotHandled(), false)
    }

    private fun createListArticle() = listOf(
        Article(1, "title1", 1586404611, "short_desc1", "avatar_url1"),
        Article(2, "title2", 1586404611, "short_desc2", "avatar_url2")
    )
}