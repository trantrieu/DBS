package com.dbs.article

import com.dbs.data.article.list.Article
import com.dbs.service.ServiceProvider
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.runners.MockitoJUnitRunner

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
    fun fetchListArticle_givenErrorWithService_returnsFailureResult() {
        `when`(serviceProvider.getArticles()).thenReturn(Single.error(Exception("Exception")))

        val actual = subject.fetchListArticle().test()

        actual.assertValue {
            it is ArticleListResult.Failure && it.message == "Exception"
        }
    }

    @Test
    fun fetchListArticle_givenInOrderList_returnsSortedListSuccessful() {
        val list = listOf(
            createArticle(3, 3),
            createArticle(2, 2),
            createArticle(4, 4),
            createArticle(1, 1)
        )
        `when`(serviceProvider.getArticles()).thenReturn(Single.just(list))

        val actual = subject.fetchListArticle().test()

        actual.assertValue {
            it is ArticleListResult.Success
                    && it.articleList[0].id == 1
                    && it.articleList[1].id == 2
                    && it.articleList[2].id == 3
                    && it.articleList[3].id == 4
        }
    }

    private fun createArticle() = Article(1, "title", 1L, "description1", "avatar1")
    private fun createArticle(id: Int, lastUpdate: Long) =
        Article(id, "title", lastUpdate, "description1", "avatar1")
}