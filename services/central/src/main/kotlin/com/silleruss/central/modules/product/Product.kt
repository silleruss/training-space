package com.silleruss.central.modules.product

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "products")
class Product(
    @Id
    val id: Long = 0,

    @Column
    val name: String = ""

    // TODO: implements
)