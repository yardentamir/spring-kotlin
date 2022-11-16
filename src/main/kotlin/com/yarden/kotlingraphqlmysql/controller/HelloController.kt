package com.yarden.kotlingraphqlmysql.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {
    var logger: Logger = LoggerFactory.getLogger(javaClass)

    @GetMapping("/hello")
    fun hello(): String {
//        logger.error("HEY")
        return "Hello World!"
    }
}