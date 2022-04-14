package com.monsieur.cloy.guitarstorecleanarchitecture.presentation.main.basket

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.monsieur.cloy.domain.models.BasketItem
import com.monsieur.cloy.domain.models.Product
import com.monsieur.cloy.guitarstorecleanarchitecture.R
import com.monsieur.cloy.guitarstorecleanarchitecture.utilits.arrayImages

class BasketItemRecyclerAdapter(val context: Context):
    RecyclerView.Adapter<BasketItemRecyclerAdapter.ViewHolder>() {

    var basketItems: ArrayList<BasketItem>? = null

    private var deleteListener: ((basketItem: BasketItem) -> Unit)? = null

    fun setOnDeleteListener(l: (basketItem: BasketItem) -> Unit){
        deleteListener = l
    }

    private var plusOneListener: ((basketItem: BasketItem) -> Unit)? = null

    fun setOnPlusOneListener(l: (basketItem: BasketItem) -> Unit){
        plusOneListener = l
    }

    private var minusOneListener: ((basketItem: BasketItem) -> Unit)? = null

    fun setOnMinusOneListener(l: (basketItem: BasketItem) -> Unit){
        minusOneListener = l
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setItems(basketItems : ArrayList<BasketItem>?){
        this.basketItems = basketItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.basket_item_card, parent, false)
        return ViewHolder(v)
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(basketItems != null && basketItems!!.isNotEmpty()){
            val item = basketItems!![position]

            if(item.product == null){
                return
            }

            Glide.with(context)
                .load(arrayImages[item.product!!.imagesId][0])
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(holder.image)

            holder.quantity.text = item.quantity.toString() + " шт."

            holder.availableQuantity.text = "Доступно " + item.product!!.quantity + " шт."

            holder.productName.text = item.product?.firm?.firmName + " " + item.product?.model

            holder.productPrice.text = (item.quantity * Product.calculatePrice(item.product!!.price, item.product!!.discount)).toString() + " р."

            holder.delete.setOnClickListener {
                basketItems?.removeAt(position)
                deleteListener?.invoke(item)
                notifyDataSetChanged()
            }

            holder.plusOne.setOnClickListener{
                if(item.quantity < item.product!!.quantity){
                    item.quantity += 1
                    plusOneListener?.invoke(item)
                    holder.quantity.text = item.quantity.toString() + " шт."
                    holder.productPrice.text = (item.quantity * Product.calculatePrice(item.product!!.price, item.product!!.discount)).toString() + " р."
                }
            }

            holder.minusOne.setOnClickListener {
                if(item.quantity > 0){
                    item.quantity -= 1
                    minusOneListener?.invoke(item)
                    if(item.quantity == 0){
                        basketItems?.removeAt(position)
                        notifyDataSetChanged()
                    }else{
                        holder.quantity.text = item.quantity.toString() + " шт."
                        holder.productPrice.text = (item.quantity * Product.calculatePrice(item.product!!.price, item.product!!.discount)).toString() + " р."
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return if(basketItems != null){
            basketItems!!.size
        } else{
            0
        }
    }



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.product_image)
        var productName: TextView = itemView.findViewById(R.id.product_name)
        var productPrice: TextView = itemView.findViewById(R.id.product_price)
        var delete: Button = itemView.findViewById(R.id.delete)
        var plusOne: Button = itemView.findViewById(R.id.plus_one)
        var minusOne: Button = itemView.findViewById(R.id.minus_one)
        var availableQuantity: TextView = itemView.findViewById(R.id.available_quantity)
        val quantity: TextView = itemView.findViewById(R.id.quantity)
    }
}