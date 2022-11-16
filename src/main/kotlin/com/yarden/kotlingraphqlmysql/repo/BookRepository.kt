package com.yarden.kotlingraphqlmysql.repo

import com.yarden.kotlingraphqlmysql.entity.Books
import org.springframework.data.repository.CrudRepository

interface BookRepository : CrudRepository<Books, Long>