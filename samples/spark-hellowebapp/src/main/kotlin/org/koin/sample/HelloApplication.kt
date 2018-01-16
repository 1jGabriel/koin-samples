package org.koin.sample

import org.koin.dsl.module.applicationContext
import org.koin.spark.controller
import org.koin.spark.runControllers
import org.koin.spark.start
import spark.kotlin.get

val helloAppModule = applicationContext {
    bean { HelloServiceImpl(get()) as HelloService }
    bean { HelloRepositoryImpl() as HelloRepository }
    controller { HelloController(get()) }
}

interface HelloRepository {
    fun getHello(): String
}

class HelloRepositoryImpl : HelloRepository {
    override fun getHello(): String = "Spark & Koin"
}

interface HelloService {
    fun sayHello(): String
}

class HelloServiceImpl(val helloRepository: HelloRepository) : HelloService {
    override fun sayHello() = "Hello ${helloRepository.getHello()} !"
}

class HelloController(val service: HelloService) {
    init {
        get("/hello") {
            service.sayHello()
        }
    }
}

fun main(vararg args: String) {
    // Spark
    start(modules = listOf(helloAppModule)) {
        // Controllers
        runControllers()
    }
}