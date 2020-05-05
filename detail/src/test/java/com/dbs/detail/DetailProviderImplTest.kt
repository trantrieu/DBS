package com.dbs.detail

import com.dbs.data.article.detail.Detail
import com.dbs.data.article.list.Article
import com.dbs.service.ServiceProvider
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class DetailProviderImplTest {

    @InjectMocks
    private lateinit var subject: DetailProviderImpl

    @Mock
    private lateinit var serviceProvider: ServiceProvider

    @Test
    fun fetchDetailWithArticle_givenIdAndArticleList_returnsDetailResultSuccess() {
        val listArticle = createListArticle()
        `when`(serviceProvider.getDetail(1)).thenReturn(Single.just(createDetail(listArticle[0])))

        val actual = subject.fetchDetailWithArticle(1, listArticle).test()

        actual.assertValue {
            it is DetailResult.Success
                    && it.detail.id == 1
                    && it.detail.text == "long text"
                    && it.detail.article!!.id == 1
                    && it.detail.article!!.avatar == "avatar_url1"
                    && it.detail.article!!.shortDescription == "short_desc1"
                    && it.detail.article!!.lastUpdate == 1586404611L
                    && it.detail.article!!.title == "title1"
        }
    }

    @Test
    fun fetchDetailWithArticle_givenServiceFailure_returnsDetailResultFailure() {
        val listArticle = createListArticle()
        `when`(serviceProvider.getDetail(1)).thenReturn(Single.error(Exception("Exception")))

        val actual = subject.fetchDetailWithArticle(1, listArticle).test()

        actual.assertValue {
            it is DetailResult.Failure
                    && it.message == "Exception"
        }
    }

    @Test
    fun fetchDetailWithArticle_cannotFindArticle_returnsDetailResultFailure() {
        val listArticle = createListArticle()
        `when`(serviceProvider.getDetail(3)).thenReturn(Single.just(createDetail(listArticle[0])))

        val actual = subject.fetchDetailWithArticle(3, listArticle).test()

        actual.assertValue {
            it is DetailResult.Failure
                    && it.message == "Generic failure"
        }
    }

    private fun createListArticle() = listOf(
        Article(1, "title1", 1586404611, "short_desc1", "avatar_url1"),
        Article(2, "title2", 1586404611, "short_desc2", "avatar_url2")
    )

    private fun createDetail(article: Article) = Detail(article.id, "long text")
}