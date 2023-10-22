package com.example.foodapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.databinding.ItemListLinearBinding
import com.example.foodapp.databinding.ItemMenuBinding
import com.example.foodapp.model.DataCategory
import com.example.foodapp.model.DataMenu

class CategoryAdapter(var listMenu: List<DataCategory>, var onItemClick: ((DataCategory) -> Unit)): RecyclerView.Adapter<com.example.foodapp.adapter.CategoryAdapter.ViewHolder> (){
    class ViewHolder(var binding: ItemMenuBinding): RecyclerView.ViewHolder(binding.root){

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): com.example.foodapp.adapter.CategoryAdapter.ViewHolder {
        val view = ItemMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: com.example.foodapp.adapter.CategoryAdapter.ViewHolder, position: Int) {
        holder.binding.tvNamaMenu.text = listMenu[position].nama

        Glide.with(holder.itemView).load(listMenu[position].imageUrl).into(holder.binding.ivMenu)
        holder.binding.cvItemCard.setOnClickListener {
            onItemClick(listMenu[position])
        }
    }

    override fun getItemCount(): Int {
        return listMenu.size
    }
}