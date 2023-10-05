package com.example.foodapp.adapter;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.data.local.database.entity.FoodEntity
import com.example.foodapp.databinding.ItemListCartBinding


class CartAdapter(
    private val itemClick: (FoodEntity) -> Unit,
    private val delete: (FoodEntity) -> Unit,
    private val decrement: (FoodEntity) -> Unit,
    private val increment: (FoodEntity) -> Unit,
) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding =
            ItemListCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding, itemClick, delete, decrement, increment)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount(): Int = items.size

    class CartViewHolder(
        private val binding: ItemListCartBinding,
        val itemClick: (FoodEntity) -> Unit,
        val delete: (FoodEntity) -> Unit,
        val decrement: (FoodEntity) -> Unit,
        val increment: (FoodEntity) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(item: FoodEntity) {
            with(item) {
                itemView.setOnClickListener { itemClick(this) }
                with(binding) {
                    deleteCart.setOnClickListener {
                        delete(item)
                    }
                    btnIncrement.setOnClickListener {
                        increment(item)
                    }
                    btnDecrement.setOnClickListener {
                        decrement(item)
                    }
                    Glide.with(itemView).load(image).into(ivMenuCartImg)
                    tvMenuPriceCart.text = harga.toString()
                    tvMenuNameCart.text = name
                    number.text = stock.toString()
                }
            }

        }
    }

}