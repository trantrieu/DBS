package com.dbs.uitestsupport

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Buffer

class WebServerRobot(private val mockWebServer: MockWebServer) {

    fun mockArticleListSuccessful() {
        val fileInputStream = javaClass.classLoader.getResourceAsStream("article/get.json")

        Buffer().use { buffer ->
            val mockResponse =
                MockResponse().setBody(buffer.readFrom(fileInputStream))
            mockResponse.setResponseCode(200)
            mockWebServer.enqueue(mockResponse)
        }
    }

    fun mockArticleFailure(errorCode: Int = 500) {
        val mockResponse = MockResponse().setResponseCode(errorCode)
        mockWebServer.enqueue(mockResponse)
    }

}