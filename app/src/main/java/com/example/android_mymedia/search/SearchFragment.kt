package com.example.android_mymedia.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import coil.Coil.reset
import com.example.android_mymedia.databinding.SearchFragmentBinding
import com.example.android_mymedia.home.adapter.BtnsAdapter
import com.example.android_mymedia.home.data.model.ButtonModel


class SearchFragment : Fragment() {
    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy {
        ViewModelProvider(this,SearchViewModelFactory())[SearchViewModel::class.java]
    }
    private val searchAdapter by lazy {
        SearchAdapter()
    }
    private val categoryAdapter by lazy {
        CategoryAdapter(
            onClicked = { item ->
                reset()
                setCategory(item.category)
                setSearchQuery(item.category)
                binding.searchRecyclerview.scrollToPosition(0)
            }
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SearchFragmentBinding.inflate(inflater, container, false)
        binding.searchBtn.setOnClickListener {
            val query = binding.edSearch.text.toString()
            viewModel.searchWithQuery(query)
        }
        binding.searchEndBtn.setOnClickListener{
            binding.edSearch.text.clear()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initView()
        Log.d("SearchFragment", "searchAdapter: $searchAdapter")
        Log.d("SearchFragment", "categoryAdapter: $categoryAdapter")
    }

    private fun initView() = with(binding) {
        searchRecyclerview.adapter = searchAdapter
        searchRecyclerview.layoutManager=LinearLayoutManager(requireContext())
        categoryRecyclerView.adapter=categoryAdapter
    }
    private fun initViewModel() {
        with(viewModel) {
            searchList.observe(viewLifecycleOwner) {
                searchAdapter.submitList(it)
                Log.d("SearchFragment", "searchList updated: $it")
            }
            searchCategory.observe(viewLifecycleOwner) {
                if (it != null) {
                    categoryAdapter.submitList(it)
                    Log.d("리스폰", searchCategory.value.toString())
                    if (it.size == 10){
                        binding.searchRecyclerview.scrollToPosition(0)
                    }
                }
            }
            searchCategory.observe(viewLifecycleOwner) {
                if (it != null) {
                    categoryAdapter.submitList(it)
                    Log.d("shh", it.toString())
                }
            }
        }
    }
    private fun setCategory(category: String) = with(viewModel) {
        viewModel.setCategory(category)
    }
    private fun reset() = with(viewModel) {
        reset()
    }
    private fun setSearchQuery(category: String) = with(viewModel){
        setSearchQuery(category)
        searchWithQuery(category)

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}