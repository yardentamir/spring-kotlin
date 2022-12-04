package com.yarden.kotlingraphqlmysql.controller.query

import com.yarden.kotlingraphqlmysql.entity.Books
import com.yarden.kotlingraphqlmysql.entity.ViewBook
import com.yarden.kotlingraphqlmysql.repo.BookRepository
import org.springframework.stereotype.Controller
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping

fun Books.toView() = ViewBook(idbooks, title, des, pages, users.map { it.first_name })

@Controller
@AllArgsConstructor
class BookQuery(private val bookRepository: BookRepository){

    @QueryMapping
    fun books():Iterable<ViewBook>{
        return bookRepository.findAll().map { it.toView() }
    }

//    @SchemaMapping(typeName="ViewBook", field="users")
//    fun
}

