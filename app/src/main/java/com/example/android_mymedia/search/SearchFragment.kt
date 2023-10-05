package com.example.android_mymedia.search

import android.content.Intent
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
import com.example.android_mymedia.detail.DetailActivity
import com.example.android_mymedia.home.adapter.BtnsAdapter
import com.example.android_mymedia.home.data.model.ButtonModel
import com.example.android_mymedia.search.searchdata.toVideoEntity


class SearchFragment : Fragment() {
    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy {
        ViewModelProvider(this, SearchViewModelFactory())[SearchViewModel::class.java]
    }
    private val searchAdapter by lazy {
        SearchAdapter(
            onItemClicked = { searchListModel ->
                val item = searchListModel.toVideoEntity()
                Intent(requireContext(), DetailActivity::class.java).apply {
                    putExtra(DetailActivity.EXTRA_DATA, item)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.run { requireContext().startActivity(this) }
            }
        )
    }
    private val categoryAdapter by lazy {
        CategoryAdapter(
            onClicked = { item ->
                searchWithCategory(item)
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SearchFragmentBinding.inflate(inflater, container, false)
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
        searchRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        categoryRecyclerView.adapter = categoryAdapter

        searchBtn.setOnClickListener {
            val query = binding.edSearch.text.toString()
            viewModel.searchWithQuery(query)
        }
        searchEndBtn.setOnClickListener {
            binding.edSearch.text.clear()
        }
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
                }
            }
        }
    }

    private fun searchWithCategory(item: ButtonModel) = with(viewModel) {
        getSearchWithCategory(item.category)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}