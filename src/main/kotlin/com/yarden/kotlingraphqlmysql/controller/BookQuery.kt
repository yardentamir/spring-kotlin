package com.yarden.kotlingraphqlmysql.controller

import com.yarden.kotlingraphqlmysql.entity.Books
import com.yarden.kotlingraphqlmysql.entity.Users
import com.yarden.kotlingraphqlmysql.entity.ViewBook
import com.yarden.kotlingraphqlmysql.entity.ViewUser
import com.yarden.kotlingraphqlmysql.repo.BookRepository
import com.yarden.kotlingraphqlmysql.repo.UserRepository
import org.springframework.stereotype.Controller
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping

fun Books.toView() = ViewBook(idbooks, title, des, pages, users.map { it.first_name })
fun Users.toView() = ViewUser(idusers, first_name, last_name, age, books.map { it.title })

@Controller
@AllArgsConstructor
class BookQuery(private val bookRepository: BookRepository, private val userRepository: UserRepository){

    @QueryMapping
    fun books():Iterable<ViewBook>{
        return bookRepository.findAll().map { it.toView() }
    }

    @QueryMapping
    fun users():Iterable<ViewUser>{
        return userRepository.findAll().map { it.toView() }
    }

//    @SchemaMapping(typeName="ViewBook", field="users")
//    fun
}

