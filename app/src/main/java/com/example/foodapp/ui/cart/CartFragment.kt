package com.example.foodapp.ui.cart

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.R
import com.example.foodapp.adapter.CartAdapter
import com.example.foodapp.data.local.database.AppDatabase
import com.example.foodapp.data.local.database.entity.FoodEntity

import com.example.foodapp.databinding.FragmentCartBinding

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    lateinit var cartViewModel: CartViewModel
    lateinit var cartViewModelApi: CartViewModelApi

    var dbFood: AppDatabase? = null
    private lateinit var cartAdapter: CartAdapter
    var totalHarga = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageView2.setOnClickListener{
            findNavController().navigateUp()
        }
        cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        cartViewModelApi = ViewModelProvider(this).get(CartViewModelApi::class.java)
        setVM()
        cartViewModel.getFoodObserver().observe(viewLifecycleOwner) {
            cartAdapter.setData(it as ArrayList<FoodEntity>)
        }
        GlobalScope.launch {
            cartViewModel.getAllData()
        }

        cartAdapter.default ={item ->
            val namaMenu = item.name
            val jumlah = item.stock
            val hargaAwal = item.price.replace("[^0-9]".toRegex(), "").toInt() / item.stock
            val totalHargaSementara = item.price.replace("[^0-9]".toRegex(), "").toInt()
            val hargaBaru = hargaAwal * (item.stock)
            totalHarga += hargaBaru - totalHargaSementara
            binding.btnPesan.setOnClickListener {
                cartViewModelApi.postOrder(" ",totalHarga,namaMenu,jumlah)
            }

        }
        cartAdapter.plusCount = { item ->
            GlobalScope.launch {
                val hargaAwal = item.price.replace("[^0-9]".toRegex(), "").toInt() / item.stock
                val totalHargaSementara = item.price.replace("[^0-9]".toRegex(), "").toInt()
                val hargaBaru = hargaAwal * (item.stock + 1)
                totalHarga += hargaBaru - totalHargaSementara
                cartViewModel.updateCount(item.stock + 1, item.id, hargaBaru)
                cartViewModel.getAllData()

                activity?.runOnUiThread {
                    val tvTotalPrice = view.findViewById<TextView>(R.id.tv_total_harga)
                    tvTotalPrice.text = "Rp. $totalHarga"
                }
            }
        }

        cartAdapter.minCount = { item ->
            GlobalScope.launch {
                val hargaAwal = item.price.replace("[^0-9]".toRegex(), "").toInt() / item.stock
                val totalHargaSementara = item.price.replace("[^0-9]".toRegex(), "").toInt()
                if (item.stock > 1) {
                    val hargaBaru = hargaAwal * (item.stock - 1)
                    totalHarga += hargaBaru - totalHargaSementara
                    cartViewModel.updateCount(item.stock - 1, item.id, hargaBaru)
                    cartViewModel.getAllData()

                    activity?.runOnUiThread {
                        val tvTotalPrice = view.findViewById<TextView>(R.id.tv_total_harga)
                        tvTotalPrice.text = "Rp. $totalHarga"
                    }
                }
            }
        }

    }


    fun setVM() {
        cartAdapter = CartAdapter(ArrayList())
        binding.rvCart.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvCart.adapter = cartAdapter
    }

    override fun onStart() {
        super.onStart()
    }

}