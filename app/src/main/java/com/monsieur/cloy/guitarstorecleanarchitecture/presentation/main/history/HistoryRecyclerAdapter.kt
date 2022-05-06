package com.monsieur.cloy.guitarstorecleanarchitecture.presentation.main.history

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.monsieur.cloy.domain.models.History
import com.monsieur.cloy.guitarstorecleanarchitecture.R
import com.monsieur.cloy.guitarstorecleanarchitecture.utilits.arrayImages
import java.time.format.DateTimeFormatter

class HistoryRecyclerAdapter(val context: Context) :
    RecyclerView.Adapter<HistoryRecyclerAdapter.ViewHolder>() {

    var historyItems: ArrayList<History>? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(historyItems : ArrayList<History>?){
        this.historyItems = historyItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.history_item_card, parent, false)
        return HistoryRecyclerAdapter.ViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(historyItems != null && historyItems!!.isNotEmpty()){
            val item = historyItems!![position]

            if(item.product == null){
                return
            }

            Glide.with(context)
                .load(arrayImages[item.product!!.imagesId][0])
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(holder.image)

            holder.quantity.text = "Количество: " + item.quantity.toString() + " шт."
            holder.dateTime.text = item.dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy mm:HH"))
            holder.address.text = item.address

            holder.productName.text = item.product!!.firm.firmName + " " + item.product!!.model

            holder.price.text = (item.price * item.quantity).toString() + " р."
        }
    }

    override fun getItemCount(): Int {
        return if(historyItems != null){
            historyItems!!.size
        } else{
            0
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.product_image)
        var dateTime: TextView = itemView.findViewById(R.id.date)
        var productName: TextView = itemView.findViewById(R.id.product_name)
        var price: TextView = itemView.findViewById(R.id.price)
        var quantity: TextView = itemView.findViewById(R.id.quantity)
        var address: TextView = itemView.findViewById(R.id.address)
    }
}