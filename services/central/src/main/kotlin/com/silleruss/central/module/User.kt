package com.silleruss.central.module

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "users")
class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    val id: Int = 0

    @Column
    val nickname: String = ""

    @Column
    val email: String = ""

    @Column
    val createdAt: LocalDateTime = LocalDateTime.now()

    @Column
    val updatedAt: LocalDateTime = LocalDateTime.now()

}