//package com.example.foodapp.ui.confirm
//
//import androidx.lifecycle.ViewModelProvider
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.core.content.ContextCompat
//import androidx.core.view.isVisible
//import androidx.navigation.Navigation
//import androidx.navigation.findNavController
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.foodapp.R
//import com.example.foodapp.adapter.CartAdapter
//import com.example.foodapp.adapter.ConfirmAdapter
//import com.example.foodapp.data.local.database.AppDatabase
//import com.example.foodapp.data.repository.FoodRepository
//import com.example.foodapp.databinding.FragmentCartBinding
//import com.example.foodapp.databinding.FragmentConfirmBinding
//import com.example.foodapp.ui.cart.CartViewModel
//import com.example.foodapp.utils.viewModelFactory
//import com.google.android.material.dialog.MaterialAlertDialogBuilder
//
//class ConfirmFragment : Fragment() {
//
//    private var _binding: FragmentConfirmBinding? = null
//    private val binding get() = _binding!!
//    private val viewModel: ConfirmViewModel by viewModelFactory {
//        ConfirmViewModel(FoodRepository(AppDatabase.getInstance(requireContext()).foodDao()))
//    }
//    private lateinit var confirmAdapter: ConfirmAdapter
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentConfirmBinding.inflate(layoutInflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        viewModel.getAllFood()
//        viewModel.food.observe(viewLifecycleOwner) {
//            if (it.isEmpty()) {
//                binding.rvPemesanan.isVisible = false
//                binding.tvEmpty.isVisible = true
//            } else {
//                binding.rvPemesanan.isVisible = true
//                binding.tvEmpty.isVisible = false
//                confirmAdapter = ConfirmAdapter(
//                    { food ->
//                        val bundle = Bundle()
//                        bundle.putString("menu", food.name)
//                        bundle.putInt("harga", food.price)
//                        bundle.putInt("imeage", food.image)
//                        bundle.putString("keterangan", food.description)
//                        bundle.putString("lokasi", food.location)
//                        Navigation.findNavController(view).navigate(
//                            R.id.action_cartFragment_to_detailFragment, bundle
//                        )///memindahkan fragment ke fragment lainnya
//                    },
//                    { food ->
//                        viewModel.deleteFood(food)
//                        viewModel.getAllFood()
//                    }
//                )
//                confirmAdapter.setItems(it)
//                binding.rvPemesanan.adapter = confirmAdapter
//                binding.rvPemesanan.setHasFixedSize(true)
//                binding.rvPemesanan.layoutManager = LinearLayoutManager(requireContext())
//            }
//            binding.btnPesan.setOnClickListener { view ->
//                if (it.isEmpty()) {
//                    Toast.makeText(
//                        requireContext(), "Pemesanan tidak boleh kosong!", Toast.LENGTH_SHORT
//                    ).show()
//                } else {
//                    MaterialAlertDialogBuilder(
//                        requireContext(),
//                        com.google.android.material.R.style.MaterialAlertDialog_Material3_Body_Text_CenterStacked
//                    ).setMessage("Apakah anda yakin ingin membeli pesanan ini?")
//                        .setNegativeButton("No") { act, _ -> act.dismiss() }
//                        .setPositiveButton("Yes") { _, _ ->
//                            viewModel.deleteALl()
//                            MaterialAlertDialogBuilder(requireContext()).setMessage("Terima kasih anda telah membeli pemesanan ini!")
//                                .setPositiveButton("OK") { act, _ ->
//                                    Navigation.findNavController(view)
//                                        .navigate(R.id.action_confirmFragment_to_homeFragment)
//                                }.show()
//                        }
//                        .show()
//                }
//            }
//        }
//        binding.ivBack.setOnClickListener {
//            it.findNavController().popBackStack()
//        }
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        _binding = null
//    }
//
//}