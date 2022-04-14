package com.monsieur.cloy.domain.models

import kotlin.math.roundToInt

class Product(id: Int, popularity: Int, quantity: Int, imagesId: Int, firm: Firm, type: ProductType, model: String, specification: String, isShow: Boolean, price: Int, discount: Int) {

    val id: Int

    var popularity: Int

    var quantity: Int

    var imagesId: Int

    var firm: Firm
    var firmId: Int = 0

    var type: ProductType
    var typeId: Int = 0

    var model: String

    var specification: String

    var isShow: Boolean

    var price: Int

    var discount: Int

    init {
        this.id = id
        this.popularity = popularity
        this.quantity = quantity
        this.imagesId = imagesId
        this.firm = firm
        this.firmId = firm.id
        this.type = type
        this.typeId = type.id
        this.model = model
        this.specification = specification
        this.isShow = isShow
        this.price = price
        this.discount = discount
    }

    companion object{
        fun calculatePrice(price: Int, discount: Int): Int{
            return (price.toFloat() - (discount.toFloat()/100 * price.toFloat())).roundToInt()
        }
    }
}