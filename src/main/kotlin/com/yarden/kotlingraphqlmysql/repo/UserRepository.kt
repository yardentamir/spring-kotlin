package com.yarden.kotlingraphqlmysql.repo

import com.yarden.kotlingraphqlmysql.entity.Users
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<Users, Long> {
//    fun findByNameStartingWith(prefix: String): Iterable<Users>

    @Query("SELECT a FROM Users a WHERE a.first_name LIKE concat('%', :suffix)")
    fun search(suffix: String): Iterable<Users>

    @Query("SELECT a FROM Users a WHERE a.first_name LIKE concat('%', :firstname) AND a.idusers = :id")
    fun searchByIdAndFName(id: Long, firstname: String): Iterable<Users>
}