package com.example.foodapp.ui.cart

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.R
import com.example.foodapp.adapter.CartAdapter
import com.example.foodapp.data.local.database.AppDatabase
import com.example.foodapp.data.repository.FoodRepository
import com.example.foodapp.databinding.FragmentCartBinding
import com.example.foodapp.databinding.FragmentDetailBinding
import com.example.foodapp.ui.detail.DetailViewModel
import com.example.foodapp.utils.viewModelFactory

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CartViewModel by viewModelFactory {
        CartViewModel(FoodRepository(AppDatabase.getInstance(requireContext()).foodDao()))
    }
    private lateinit var cartAdapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllFood()
        viewModel.getTotalFood()
        viewModel.food.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.rvCart.isVisible = false
                binding.tvEmpty.isVisible = true
            } else {
                binding.rvCart.isVisible = true
                binding.tvEmpty.isVisible = false
                cartAdapter = CartAdapter(
                    { food ->
                        val bundle = Bundle()
                        bundle.putString("menu", food.name)
                        bundle.putInt("harga", food.price)
                        bundle.putInt("imeage", food.image)
                        bundle.putString("keterangan", food.description)
                        bundle.putString("lokasi", food.location)
                        Navigation.findNavController(view).navigate(
                            R.id.action_cartFragment_to_detailFragment, bundle
                        )///memindahkan fragment ke fragment lainnya
                    },
                    { food ->
                        viewModel.deleteFood(food)
                        viewModel.getAllFood()
                        viewModel.getTotalFood()
                    },
                    {
                        if (it.stock > 1) {
                            viewModel.updateStock(it.stock - 1, it.id!!, (it.stock - 1) * it.price)
                            viewModel.getAllFood()
                            viewModel.getTotalFood()
                        } else {
                            Toast.makeText(
                                requireContext(), "Stock tidak boleh 0!", Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    {
                        viewModel.updateStock(it.stock + 1, it.id!!, (it.stock + 1) * it.price)
                        viewModel.getAllFood()
                        viewModel.getTotalFood()
                    },
                )
                cartAdapter.setItems(it)
                binding.rvCart.adapter = cartAdapter
                binding.rvCart.setHasFixedSize(true)
                binding.rvCart.layoutManager = LinearLayoutManager(requireContext())
            }
            binding.btnPesan.setOnClickListener { view ->
                if (it.isEmpty()) {
                    Toast.makeText(
                        requireContext(), "Pemesanan tidak boleh kosong!", Toast.LENGTH_SHORT
                    ).show()
                } else {
                    view.findNavController().navigate(R.id.action_cartFragment_to_confirmFragment)
                }
            }
        }
        viewModel.harga.observe(viewLifecycleOwner) {
            if (it != 0) {
                binding.tvTotalHarga.text = it.toString()
            } else {
                binding.tvTotalHarga.text = "0"
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}