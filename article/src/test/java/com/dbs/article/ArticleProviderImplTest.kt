package com.dbs.article

import com.dbs.data.article.list.Article
import com.dbs.service.ServiceProvider
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.runners.MockitoJUnitRunner
import java.lang.Exception

@RunWith(MockitoJUnitRunner::class)
internal class ArticleProviderImplTest {

    @InjectMocks
    private lateinit var subject: ArticleProviderImpl

    @Mock
    private lateinit var serviceProvider: ServiceProvider

    @Test
    fun fetchListArticle_givenListArticleWithService_returnsSuccessfulResult() {
        `when`(serviceProvider.getArticles()).thenReturn(Single.just(listOf(createArticle())))

        val actual = subject.fetchListArticle().test()

        actual.assertValue {
            it is ArticleListResult.Success && it.articleList == listOf(createArticle())
        }
    }

    @Test
    fun fetchListArticle_givenErrorWithService_returnsSuccessfulFailure() {
        `when`(serviceProvider.getArticles()).thenReturn(Single.error(Exception("Exception")))

        val actual = subject.fetchListArticle().test()

        actual.assertValue {
            it is ArticleListResult.Failure && it.message == "Exception"
        }
    }

    private fun createArticle() = Article(1, "title", 1L, "description1", "avatar1")
}