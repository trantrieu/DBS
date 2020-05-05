package com.dbs.service

import com.dbs.service.detail.DetailResponse
import com.dbs.service.titles.ArticleResponse
import junit.framework.Assert.assertEquals
import org.junit.Test

internal class ResponseConverterTest {

    @Test
    fun convertArticle_givenListArticleResponse_returnListArticle() {
        val actual = ResponseConverter.convertArticle(getListArticleResponse())

        assertEquals(2, actual.size)

        assertEquals(actual[0].id, 1)
        assertEquals(actual[0].avatar, "https://minotar.net/avatar/user2")
        assertEquals(actual[0].lastUpdate, 1586404611)
        assertEquals(
            actual[0].shortDescription,
            "This is article 1 short description. She wholly fat who window extent either formal. Removing welcomed civility or hastened is."
        )
        assertEquals(actual[0].title, "article 1 title")

        assertEquals(actual[1].id, 2)
        assertEquals(actual[1].avatar, "https://minotar.net/avatar/user3")
        assertEquals(actual[1].lastUpdate, 1506404611)
        assertEquals(
            actual[1].shortDescription,
            "This is article 2 short description. She wholly fat who window extent either formal. Removing welcomed civility or hastened is."
        )
        assertEquals(actual[1].title, "article 2 title")

    }

    @Test
    fun convertArticle_givenEmptyListArticleResponse_returnEmptyListArticle() {
        val actual = ResponseConverter.convertArticle(emptyList())

        assertEquals(true, actual.isEmpty())
    }

    @Test
    fun convertDetail_givenDetailResponse_returnDetail() {
        val actual = ResponseConverter.convertDetail(getDetailResponse())

        assertEquals(1, actual.id)
        assertEquals("long_text", actual.text)
    }

    private fun getListArticleResponse() = listOf(
        ArticleResponse(
            1,
            "article 1 title",
            1586404611,
            "This is article 1 short description. She wholly fat who window extent either formal. Removing welcomed civility or hastened is.",
            "https://minotar.net/avatar/user2"
        ),
        ArticleResponse(
            2,
            "article 2 title",
            1506404611,
            "This is article 2 short description. She wholly fat who window extent either formal. Removing welcomed civility or hastened is.",
            "https://minotar.net/avatar/user3"
        )
    )

    private fun getDetailResponse() = DetailResponse(1, "long_text")
}