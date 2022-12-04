package com.yarden.kotlingraphqlmysql.controller.query

import com.yarden.kotlingraphqlmysql.entity.Users
import com.yarden.kotlingraphqlmysql.entity.ViewUser
import com.yarden.kotlingraphqlmysql.repo.UserRepository
import org.springframework.stereotype.Controller
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping

fun Users.toView() = ViewUser(idusers, first_name, last_name, age, books.map { it.title })

@Controller
@AllArgsConstructor
class UsersQuery(private val userRepository: UserRepository){

    @QueryMapping
    fun users():Iterable<ViewUser>{
        return userRepository.findAll().map { it.toView() }
    }

//    @SchemaMapping(typeName="ViewBook", field="users")
//    fun
}

