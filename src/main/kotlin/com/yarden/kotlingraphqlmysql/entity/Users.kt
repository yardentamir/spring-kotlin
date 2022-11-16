package com.yarden.kotlingraphqlmysql.entity

import org.hibernate.Hibernate
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
        inverseJoinColumns = [JoinColumn(name = "idbooks")]
    )
    val books: List<Books> = listOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Users

        return idusers == other.idusers
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(idusers = $idusers )"
    }
}
