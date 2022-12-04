package com.yarden.kotlingraphqlmysql.entity

import javax.persistence.*

data class ViewUser(
    val idusers: Long,
    val first_name: String,
    val last_name: String,
    val age: Int,
    val books: Iterable<String>,
)

data class CreateUser(
    val first_name: String,
    val last_name: String,
    val age: Int,
)

@Entity
@Table(name = "users")

data class Users(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idusers: Long = 0,
    val first_name: String,
    val last_name: String,
    val age: Int,
    @ManyToMany
    @JoinTable(
        name = "users_books",
        joinColumns = [JoinColumn(name = "idusers")],
        inverseJoinColumns = [JoinColumn(name = "idbooks")],
)
    val books: List<Books> = listOf()
)
