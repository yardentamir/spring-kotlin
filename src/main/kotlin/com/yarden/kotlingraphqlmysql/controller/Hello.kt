package com.yarden.kotlingraphqlmysql.controller

import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class Hello {
    @QueryMapping(name="hello")
    fun hello(): String {
        return "hello";
    }

    @MutationMapping
    fun world(@Argument name: String): String {
        return "hello, $name"
    }
}