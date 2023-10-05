package com.example.foodapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.adapter.GridAdapter
import com.example.foodapp.adapter.ListAdapter
import com.example.foodapp.data.local.datastore.SettingDataStoreManager
import com.example.foodapp.data.repository.SettingRepository
import com.example.foodapp.databinding.FragmentHomeBinding
import com.example.foodapp.utils.DataDummy
import com.example.foodapp.utils.viewModelFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModelFactory {
        HomeViewModel(SettingRepository(SettingDataStoreManager(requireContext())))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getSetting.observe(viewLifecycleOwner) {
            if (it == true) {
                binding.switch1.isChecked = true
                listLinear()
            } else {
                binding.switch1.isChecked = false
                listGrid()
            }
        }
        binding.switch1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                listLinear()
                viewModel.setSetting(true)
            } else {
                listGrid()
                viewModel.setSetting(false)
            }
        }
    }

    private fun listGrid() {
        binding.rvMenu.adapter = GridAdapter(DataDummy.listData)
        binding.rvMenu.layoutManager = GridLayoutManager(context, 2)
    }

    private fun listLinear() {
        binding.rvMenu.adapter = ListAdapter(DataDummy.listData)
        binding.rvMenu.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}