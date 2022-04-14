package com.monsieur.cloy.guitarstorecleanarchitecture.presentation.catalog.filters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.monsieur.cloy.domain.models.Firm
import com.monsieur.cloy.domain.models.ProductType
import com.monsieur.cloy.guitarstorecleanarchitecture.R

class FilterRecyclerAdapter : RecyclerView.Adapter<FilterRecyclerAdapter.ViewHolder>() {

    private var types: ArrayList<ProductType>? = null
    private var firms: ArrayList<Firm>? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setTypes(types : ArrayList<ProductType>){
        firms = null
        this.types = types
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setFirms(firms : ArrayList<Firm>){
        types = null
        this.firms = firms
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearChecked(){
        if(types != null){
            types!!.forEach { it.isChecked = false }
            notifyDataSetChanged()
        }
        if(firms != null){
            firms!!.forEach { it.isChecked = false }
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.filter_checkbox_card, parent, false)
        return ViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(types != null){
            val type = types!![position]

            holder.text.text = type.typeName
            holder.checkBox.isChecked = type.isChecked

            holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
                type.isChecked = isChecked
            }

            holder.card.setOnClickListener {
                holder.checkBox.isChecked = !holder.checkBox.isChecked
            }
            return
        }

        if(firms != null){
            val firm = firms!![position]

            holder.text.text = firm.firmName
            holder.checkBox.isChecked = firm.isChecked

            holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
                firm.isChecked = isChecked
            }
            holder.card.setOnClickListener {
                holder.checkBox.isChecked = !holder.checkBox.isChecked
            }
            return
        }
    }

    fun getCheckedFirms(): ArrayList<Firm>{
        return if(firms == null){
            ArrayList<Firm>()
        }else{
            firms!!.filter { it.isChecked } as ArrayList<Firm>
        }
    }

    fun getCheckedTypes(): ArrayList<ProductType>{
        return if(types == null){
            ArrayList<ProductType>()
        }else{
            types!!.filter { it.isChecked } as ArrayList<ProductType>
        }
    }

    override fun getItemCount(): Int {
        if(types == null && firms == null){
            return 0
        }else if(types == null){
            return firms!!.size
        }else{
            return types!!.size
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text: TextView = itemView.findViewById(R.id.text)
        var checkBox: CheckBox = itemView.findViewById(R.id.checkbox)
        var card: ConstraintLayout = itemView.findViewById(R.id.card)
    }
}