package com.dbs.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dbs.data.article.detail.Detail
import com.dbs.data.article.list.Article
import com.dbs.testsupport.getOrAwaitValue
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @InjectMocks
    private lateinit var subject: DetailViewModel

    @Test
    fun setDetail_givenDetail_checkLiveData() {
        subject.setDetail(getDetail())

        assertEquals(subject.textLongLiveData.getOrAwaitValue(), "text")
        assertEquals(subject.avatarLiveData.getOrAwaitValue(), "avatar1")
        assertEquals(subject.titleLiveData.getOrAwaitValue(), "title1")
    }

    private fun getDetail() = Detail(1, "text", getArticle())

    private fun getArticle() = Article(1, "title1", 1, "desc1", "avatar1")

}