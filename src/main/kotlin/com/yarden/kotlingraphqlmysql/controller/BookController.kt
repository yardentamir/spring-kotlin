package com.yarden.kotlingraphqlmysql.controller

import com.yarden.kotlingraphqlmysql.entity.Books
import com.yarden.kotlingraphqlmysql.entity.CreateBook
import com.yarden.kotlingraphqlmysql.entity.ViewBook
import com.yarden.kotlingraphqlmysql.repo.BookRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

fun Books.toView() = ViewBook(idbooks, title, des, pages, users.map { it.first_name })

@RestController
@RequestMapping("books")
class BooksController(val bookRepository: BookRepository) {
    var logger: Logger = LoggerFactory.getLogger(javaClass)

    @GetMapping
    fun findAll(): Iterable<ViewBook> = bookRepository.findAll().map { it.toView() }

    // example to postman: http://localhost:8080/books

    @PostMapping
    fun create(@RequestBody createBook: CreateBook): ViewBook =
        bookRepository.save(
            Books(
                title = createBook.title,
                des = createBook.des,
                pages = createBook.pages,
            )
        ).toView()
}