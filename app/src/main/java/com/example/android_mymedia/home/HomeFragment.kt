package com.example.android_mymedia.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_mymedia.databinding.HomeFragmentBinding
import com.example.android_mymedia.home.adapter.VideoAdapter


class HomeFragment : Fragment() {

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            HomeViewModelFactory()
        )[HomeViewModel::class.java]
    }

    private val videoAdapter by lazy {
        VideoAdapter()
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

        initView()
        initViewModel()
    }


    private fun initView() = with(binding) {
        homeRvVideoList.adapter = videoAdapter
        homeRvVideoList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager

                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                val isAtEndOfList = visibleItemCount + firstVisibleItemPosition >= totalItemCount

                if (isAtEndOfList) {

                    getNextPage()

                }
            }
        })


    }

    private fun initViewModel() {
        with(viewModel) {
            categoryList.observe(viewLifecycleOwner) {
                videoAdapter.submitList(it)
                Log.d("리스폰", categoryList.value.toString())
            }
            pageToken.observe(viewLifecycleOwner) {
                Log.d("토큰", pageToken.value.toString())
            }
        }
    }
    private fun getNextPage() = with(viewModel) {
        viewModel.setNextPage()
    }


}