package com.monsieur.cloy.domain.models

class ProductType(id: Int, typeName: String) {
    val id: Int = id

    var typeName: String = typeName

    var isChecked: Boolean = false
}