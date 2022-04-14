package com.monsieur.cloy.guitarstorecleanarchitecture.presentation.catalog.product

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.monsieur.cloy.guitarstorecleanarchitecture.R
import com.smarteist.autoimageslider.SliderViewAdapter

class SliderAdapter(val context: Context): SliderViewAdapter<SliderAdapter.SliderAdapterVH>() {

    private var sliderItems: MutableList<Int> = ArrayList()

    fun setItems(sliderItems: MutableList<Int>) {
        this.sliderItems = sliderItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH? {
        val inflate: View =
            LayoutInflater.from(parent.context).inflate(R.layout.image_slider_layout_item, parent, false)
        return SliderAdapterVH(inflate)
    }

    override fun onBindViewHolder(holder: SliderAdapterVH, position: Int) {
        Glide.with(context)
            .load(sliderItems[position])
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(holder.image)
    }

    override fun getCount(): Int {
        return sliderItems.size
    }

    class SliderAdapterVH(var itemView: View) : SliderViewAdapter.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.image)
    }
}