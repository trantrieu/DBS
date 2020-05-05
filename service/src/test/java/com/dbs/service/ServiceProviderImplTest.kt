package com.dbs.service

import com.dbs.service.titles.ArticleResponse
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class ServiceProviderImplTest {

    @InjectMocks
    private lateinit var subject: ServiceProviderImpl

    @Mock
    private lateinit var service: Service

    @Test
    fun getArticles_givenServiceWithListArticleResponse_returnsListArticle() {
        `when`(service.getArticles()).thenReturn(Single.just(listResponse))

        val actual = subject.getArticles().test()
        actual.assertValue {
            it.size == 2
                    && it[0].id == 1 && it[0].title == "title1" && it[0].lastUpdate == 1586404611L && it[0].shortDescription == "short_description1" && it[0].avatar == "avatar_url1"
                    && it[1].id == 2 && it[1].title == "title2" && it[1].lastUpdate == 1586404611L && it[1].shortDescription == "short_description2" && it[1].avatar == "avatar_url2"
        }
    }

    @Test
    fun getArticles_givenServiceWithEmptyListArticleResponse_returnsEmptyListArticle() {
        `when`(service.getArticles()).thenReturn(Single.just(emptyList()))

        val actual = subject.getArticles().test()

        actual.assertValue {
            it.isEmpty()
        }
    }

    private val listResponse = listOf(
        ArticleResponse(1, "title1", 1586404611L, "short_description1", "avatar_url1"),
        ArticleResponse(2, "title2", 1586404611L, "short_description2", "avatar_url2")
    )
}