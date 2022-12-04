package com.yarden.kotlingraphqlmysql.entity

import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import org.hibernate.Hibernate
import javax.persistence.*

data class ViewBook(
    val idbooks: Long,
    val title: String,
    val des: String,
    val pages: Int,
    val users: Iterable<String>
)

data class CreateBook(
    val title: String,
    val des: String,
    val pages: Int,
)

data class EnrollInBook(
    val idbook: Long,
)

@Entity
@Table(name = "books")

data class Books(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idbooks: Long = 0,
    val title: String,
    val des: String,
    val pages: Int,
    @ManyToMany(mappedBy = "books")
    val users: List<Users> = listOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Books

        return idbooks == other.idbooks
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(idbooks = $idbooks )"
    }
}
