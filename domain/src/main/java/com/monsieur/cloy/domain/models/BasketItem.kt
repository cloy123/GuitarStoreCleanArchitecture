package com.monsieur.cloy.domain.models

class BasketItem(id: Int, quantity: Int, userId: Int, product: Product?) {

    val id: Int

    var quantity: Int

    val userId: Int

    val product: Product?
    var productId: Int = 0

    init {
        this.id = id
        this.quantity = quantity
        this.userId = userId
        this.product = product
        if (product != null) {
            this.productId = product.id
        }
    }

    constructor(quantity: Int, userId: Int, productId: Int): this(0, quantity, userId, null){
        this.productId = productId
    }
}