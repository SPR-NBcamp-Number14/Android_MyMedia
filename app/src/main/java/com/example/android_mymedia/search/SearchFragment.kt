package com.example.android_mymedia.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_mymedia.databinding.SearchFragmentBinding
import com.example.android_mymedia.home.HomeViewModel


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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initView()
    }

    private fun initView() = with(binding) {
        searchRecyclerview.adapter = searchAdapter
        searchRecyclerview.layoutManager=LinearLayoutManager(requireContext())
    }
    private fun initViewModel() {
        with(viewModel) {
            shortsList.observe(viewLifecycleOwner) {
                searchAdapter.submitList(it)
            }

        }
    }

}