package org.koin.sampleapp

import org.junit.After
import org.junit.Test
import org.koin.sampleapp.di.DatasourceProperties.SERVER_URL
import org.koin.sampleapp.di.remoteDatasourceModule
import org.koin.sampleapp.di.testLocalDatasource
import org.koin.sampleapp.di.testRemoteDatasource
import org.koin.sampleapp.di.weatherApp
import org.koin.standalone.StandAloneContext.closeKoin
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.test.KoinTest
import org.koin.test.dryRun

class DryRunTest : KoinTest {

    val defaultProperties = mapOf(SERVER_URL to "http://test.me")

    @After
    fun after() {
        closeKoin()
    }

    @Test
    fun normalConfiguration() {
        startKoin(weatherApp+ remoteDatasourceModule, properties = defaultProperties)
        dryRun()
    }

    @Test
    fun testRemoteConfiguration() {
        startKoin(testRemoteDatasource, properties = defaultProperties)
        dryRun()
    }

    @Test
    fun testLocalConfiguration() {
        startKoin(testLocalDatasource, properties = defaultProperties)
        dryRun()
    }
}