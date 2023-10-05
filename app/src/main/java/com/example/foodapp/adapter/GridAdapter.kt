package com.example.foodapp.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.R
import com.example.foodapp.databinding.ItemListGridBinding
import com.example.foodapp.model.Food


class GridAdapter(private val food: ArrayList<Food>) :
    RecyclerView.Adapter<GridAdapter.ListViewHolder>() {

    inner class ListViewHolder(val binding: ItemListGridBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(food: Food) {
            Glide.with(itemView).load(food.image).into(binding.ivMenu)
            binding.tvNamaMakanan.text = food.name
            binding.tvPriceFood.text = food.price.toString()

        }


    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ListViewHolder {
        val binding = ItemListGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return food.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(food[position])
        holder.binding.root.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("menu", food[position].name)
            bundle.putInt("harga", food[position].price)
            bundle.putInt("imeage", food[position].image)
            bundle.putString("keterangan", food[position].description)
            bundle.putString("lokasi", food[position].location)
            Navigation.findNavController(it).navigate(
                R.id.action_homeFragment_to_detailFragment,
                bundle
            )///memindahkan fragment ke fragment lainnya
        }
    }
}