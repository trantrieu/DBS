package com.dbs.uitestsupport

import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.ExternalResource
import java.io.IOException

class MockWebServerRule : ExternalResource() {

    private val mockWebServer = MockWebServer()

    @Throws(IOException::class)
    override fun before() {
        mockWebServer.start(8080)
    }

    override fun after() {
        mockWebServer.shutdown()
    }

    fun getMockWebServer() = mockWebServer
}