package org.koin.sample

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.koin.sample.util.SparkTestUtil
import org.koin.spark.start
import org.koin.spark.stop
import org.koin.standalone.StandAloneContext.closeKoin
import org.koin.standalone.get
import org.koin.test.KoinTest

class HelloControllerTest : KoinTest {

    lateinit var sparkTest: SparkTestUtil

    @Before()
    fun before() {
        val port = start(0, modules = listOf(helloAppModule)) {
            get<HelloController>()
        }
        sparkTest = SparkTestUtil(port)
    }

    @After
    fun after() {
        stop(200)
    }

    @Test
    fun `controller say hello`() {
        val response = sparkTest.get("/hello")
        assertEquals(200, response.status)
        assertEquals("Hello Spark & Koin !", response.body)
    }
}