package com.example.android_mymedia.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.android_mymedia.databinding.HomeFragmentBinding
import com.example.mymedia.home.HomeViewModel
import com.example.android_mymedia.home.adapter.CategoryAdapter
import com.example.android_mymedia.home.adapter.ShortAdapter


class HomeFragment : Fragment() {

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }
    private val shortAdapter by lazy {
        ShortAdapter()
    }
    private val categoryAdapter by lazy {
        CategoryAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    private fun initView() = with(binding) {
        homeRvShortsList.adapter = shortAdapter
        homeRvCategoryList.adapter = categoryAdapter

    }

    private fun initViewModel() {
        with(viewModel) {
            shortsList.observe(viewLifecycleOwner) {
                shortAdapter.submitList(it)
            }
            categoryList.observe(viewLifecycleOwner) {
                categoryAdapter.submitList(it)
            }
        }
    }


}