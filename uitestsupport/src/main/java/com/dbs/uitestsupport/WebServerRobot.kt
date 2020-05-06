package com.dbs.uitestsupport

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Buffer

class WebServerRobot(private val mockWebServer: MockWebServer) {

    fun mockArticleListSuccessful(): WebServerRobot {
        val fileInputStream = javaClass.classLoader.getResourceAsStream("article/get.json")

        Buffer().use { buffer ->
            val mockResponse =
                MockResponse().setBody(buffer.readFrom(fileInputStream))
            mockResponse.setResponseCode(200)
            mockWebServer.enqueue(mockResponse)
        }
        return this
    }

    fun mockResponseFailure(errorCode: Int = 500): WebServerRobot {
        val mockResponse = MockResponse().setResponseCode(errorCode)
        mockWebServer.enqueue(mockResponse)
        return this
    }

    fun mockDetailSuccessful(): WebServerRobot {
        val fileInputStream = javaClass.classLoader.getResourceAsStream("detail/get.json")

        Buffer().use { buffer ->
            val mockResponse =
                MockResponse().setBody(buffer.readFrom(fileInputStream))
            mockResponse.setResponseCode(200)
            mockWebServer.enqueue(mockResponse)
        }
        return this
    }

}