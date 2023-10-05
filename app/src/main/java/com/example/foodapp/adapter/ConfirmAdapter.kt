package com.example.foodapp.adapter;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.data.local.database.entity.FoodEntity
import com.example.foodapp.databinding.ItemListConfirmBinding


class ConfirmAdapter(
    private val itemClick: (FoodEntity) -> Unit,
    private val delete: (FoodEntity) -> Unit,
) :
    RecyclerView.Adapter<ConfirmAdapter.ConfirmViewHolder>() {


    private var items: MutableList<FoodEntity> = mutableListOf()

    fun setItems(items: List<FoodEntity>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun addItems(items: List<FoodEntity>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun clearItems() {
        this.items.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConfirmViewHolder {
        val binding =
            ItemListConfirmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConfirmViewHolder(binding, itemClick, delete)
    }

    override fun onBindViewHolder(holder: ConfirmViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount(): Int = items.size


    class ConfirmViewHolder(
        private val binding: ItemListConfirmBinding,
        val itemClick: (FoodEntity) -> Unit,
        val delete: (FoodEntity) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(item: FoodEntity) {
            with(item) {
                itemView.setOnClickListener { itemClick(this) }
                with(binding) {
                    deleteCart.setOnClickListener {
                        delete(item)
                    }
                    Glide.with(itemView).load(image).into(ivMenuCartImg)
                    tvMenuPriceCart.text = harga.toString()
                    tvMenuNameCart.text = name
                    number.text = "x$stock"
                }
            }

        }
    }

}