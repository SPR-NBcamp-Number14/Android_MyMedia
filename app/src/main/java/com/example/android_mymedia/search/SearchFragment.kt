package com.example.android_mymedia.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.android_mymedia.databinding.SearchFragmentBinding
import com.example.android_mymedia.home.adapter.SearchAdapter
import com.example.android_mymedia.home.adapter.ShortAdapter
import com.example.mymedia.home.HomeViewModel

class SearchFragment : Fragment() {


    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }
    private val searchAdapter by lazy {
        SearchAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SearchFragmentBinding.inflate(inflater, container, false)
        initViewModel()
        initView()
        return binding.root
    }

    private fun initView() = with(binding) {
        searchRecyclerview.adapter = searchAdapter


    }
    private fun initViewModel() {
        with(viewModel) {
            shortsList.observe(viewLifecycleOwner) {
                searchAdapter.submitList(it)
            }

        }
    }

}