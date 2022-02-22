package com.silleruss.central

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
class CentralApplication

fun main(args: Array<String>) {
	runApplication<CentralApplication>(*args)
}
