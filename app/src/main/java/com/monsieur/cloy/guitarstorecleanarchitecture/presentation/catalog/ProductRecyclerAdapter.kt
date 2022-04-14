package com.monsieur.cloy.guitarstorecleanarchitecture.presentation.catalog

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.monsieur.cloy.domain.models.Product
import com.monsieur.cloy.guitarstorecleanarchitecture.R
import com.monsieur.cloy.guitarstorecleanarchitecture.utilits.arrayImages
import com.monsieur.cloy.guitarstorecleanarchitecture.utilits.replaceFragment

class ProductRecyclerAdapter (val context: Context): RecyclerView.Adapter<ProductRecyclerAdapter.ViewHolder>() {

    private var products: List<Product>? = null

    private var clickListener: ((product: Product) -> Unit)? = null

    fun setOnClickListener(l: (product: Product) -> Unit){
        clickListener = l
    }

    private var addToBasketListener: ((product: Product) -> Unit)? = null

    fun setAddToBasketListener(l: (product: Product) -> Unit){
        addToBasketListener = l
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setItems(products : List<Product>){
        this.products = products
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.product_card, parent, false)
        return ViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(products != null && products!!.isNotEmpty()){
            val product = products!![position]

            Glide.with(context)
                .load(arrayImages[product.imagesId][0])
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(holder.image)

            holder.productName.text = product.firm.firmName + " " + product.model

            holder.productType.text = product.type.typeName

            if (product.quantity > 0) {
                holder.productAvailability.text = "Осталось: " + product.quantity
                holder.addToBasket.isEnabled = true
                holder.addToBasket.text = context.getString(R.string.add_to_basket)
            } else {
                holder.productAvailability.text = "Нет в наличии"
                holder.addToBasket.text = context.getString(R.string.not_available)
                holder.addToBasket.isEnabled = false
            }
            holder.productPrice.text = Product.calculatePrice(product.price, product.discount).toString() + " р."

            holder.addToBasket.setOnClickListener {
                addToBasketListener?.invoke(product)
            }

            holder.card.setOnClickListener {
                clickListener?.invoke(product)
            }
        }
    }

    override fun getItemCount(): Int {
        return if(products != null){
            products!!.size
        } else{
            0
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.product_image)
        var productName: TextView = itemView.findViewById(R.id.product_name)
        var productAvailability: TextView = itemView.findViewById(R.id.product_availability)
        var productPrice: TextView = itemView.findViewById(R.id.product_price)
        var productType: TextView = itemView.findViewById(R.id.product_type)
        var addToBasket: Button = itemView.findViewById(R.id.add_to_basket)
        var card: CardView = itemView.findViewById(R.id.card)
    }
}