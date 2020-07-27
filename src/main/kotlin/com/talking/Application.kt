package com.talking

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration

@SpringBootApplication(exclude=arrayOf(SecurityAutoConfiguration::class))
class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}