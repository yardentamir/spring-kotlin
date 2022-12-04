package com.yarden.kotlingraphqlmysql.rest

import com.yarden.kotlingraphqlmysql.entity.CreateUser
import com.yarden.kotlingraphqlmysql.entity.EnrollInBook
import com.yarden.kotlingraphqlmysql.entity.Users
import com.yarden.kotlingraphqlmysql.entity.ViewUser
import com.yarden.kotlingraphqlmysql.repo.BookRepository
import com.yarden.kotlingraphqlmysql.repo.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

fun Users.toView() = ViewUser(idusers, first_name, last_name, age, books.map { it.title })

@RestController
@RequestMapping("users")
class UsersController(val userRepository: UserRepository, val bookRepository: BookRepository) {
    var logger: Logger = LoggerFactory.getLogger(javaClass)

    @GetMapping
    fun findAll(): Iterable<ViewUser> = userRepository.findAll().map { it.toView() }

//    @GetMapping("startwith")
//    fun findByNameStartingWith(): Iterable<ViewUser> = userRepository.findByNameStartingWith("Y").map { it.toView() }

    // example to postman: http://localhost:8080/users/search/yarden2
    @GetMapping("search/{name}")
    fun searchByFName(@PathVariable name: String): List<ViewUser>? {
        try {
            return userRepository.search(name).map { it.toView() }
        } catch (e: Throwable) {
            logger.error("ERR !!!!", e)
        }
        return null
    }

    // example to postman: http://localhost:8080/users/search?usersid=1&first_name=yarden
    @GetMapping("search")
    fun searchByIdAndFName(@RequestParam usersid: Long, @RequestParam first_name: String): List<ViewUser>? {
        try {
            return userRepository.searchByIdAndFName(usersid, first_name).map { it.toView() }
        } catch (e: Throwable) {
            logger.error("ERR !!!!", e)
        }
        return null
    }

    // example to postman: http://localhost:8080/users
    @PostMapping
    fun create(@RequestBody createUser: CreateUser): ViewUser =
        userRepository.save(
            Users(
                first_name = createUser.first_name,
                last_name = createUser.last_name,
                age = createUser.age,
            )
        ).toView()

    // example to postman: http://localhost:8080/enroll/1
    // body: { "idbook": 2 }
    @PostMapping("enroll/{user_id}")
    fun enroll(@PathVariable user_id: Long, @RequestBody enrollIn: EnrollInBook): ViewUser {
        val user = userRepository.findById(user_id).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
        val book =
            bookRepository.findById(enrollIn.idbook).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }

        return userRepository.save(
            user.copy(
                books = user.books.plus(book)
            )
        ).toView()
    }

}
