package com.example.foodapp.ui.detail

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.foodapp.R
import com.example.foodapp.data.local.database.AppDatabase
import com.example.foodapp.data.local.database.dao.FoodDao
import com.example.foodapp.data.local.database.entity.FoodEntity
import com.example.foodapp.data.repository.FoodRepository
import com.example.foodapp.databinding.FragmentDetailBinding
import com.example.foodapp.databinding.FragmentHomeBinding
import com.example.foodapp.ui.home.HomeViewModel
import com.example.foodapp.utils.viewModelFactory

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailViewModel by viewModelFactory {
        DetailViewModel(FoodRepository(AppDatabase.getInstance(requireContext()).foodDao()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        val ambilDataMenu = bundle!!.getString("menu")
        val ambilDataharga = bundle!!.getInt("harga")
        val ambilDataimeage = bundle!!.getInt("imeage")
        val ambilDataketerangan = bundle!!.getString("keterangan")
        val ambilDatakelokasi = bundle!!.getString("lokasi")
        binding.tvFoodName.text = ambilDataMenu
        binding.tvHargaDetail.text = ambilDataharga.toString()
        Glide.with(requireContext()).load(ambilDataimeage).into(binding.ivProvImg)
        binding.tvLocation.text = ambilDatakelokasi
        binding.keterangan.text = ambilDataketerangan

        Glide.with(requireContext()).load(ambilDataimeage).into(binding.banner)
        binding.ivBack.setOnClickListener {
            it.findNavController().navigate(R.id.action_detailFragment_to_cartFragment)
        }
        binding.ivMaps.setOnClickListener {
            val data = binding.tvLocation.text.toString()
            val uri = Uri.parse("https://www.google.com/maps/search/$data")
            val mapIntent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(mapIntent)
        }
        viewModel.number.observe(viewLifecycleOwner) { jumlah ->
            binding.number.text = jumlah.toString()
            val number = jumlah * ambilDataharga
            binding.btnAddCart.text = "Tambah keranjang Rp. $number"
            binding.btnAddCart.setOnClickListener {
                if (jumlah != 0) {
                    viewModel.addFood(
                        FoodEntity(
                            null,
                            ambilDataimeage,
                            ambilDataMenu!!,
                            ambilDataharga,
                            ambilDataketerangan!!,
                            ambilDatakelokasi!!,
                            jumlah,
                            number
                        )
                    )
                    it.findNavController().popBackStack()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Tidak bisa menambahkan karna jumlah 0!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        binding.btnIncrement.setOnClickListener {
            viewModel.increment()
        }
        binding.btnDecrement.setOnClickListener {
            viewModel.decrement()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}