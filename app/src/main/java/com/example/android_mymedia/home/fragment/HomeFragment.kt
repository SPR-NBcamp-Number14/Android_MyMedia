package com.example.android_mymedia.home.fragment

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
import com.example.android_mymedia.home.adapter.BtnsAdapter
import com.example.android_mymedia.home.viewmdoel.HomeViewModel
import com.example.android_mymedia.home.viewmdoel.HomeViewModelFactory
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
    private val btnsAdapter by lazy {
        BtnsAdapter()
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

        //버튼 RecyclerView
        homeRvBtnsList.adapter = btnsAdapter

        //메인 RecyclerView
        homeRvVideoList.adapter = videoAdapter
        homeRvVideoList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager

                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                val isAtEndOfList = visibleItemCount + firstVisibleItemPosition >= totalItemCount

//                !homeRvVideoList.canScrollHorizontally(1)

                if (isAtEndOfList) {

                    getNextPage() // 주석 시 홈 api 사용 x

                }
            }
        })


    }

    private fun initViewModel() {
        with(viewModel) {
            categoryList.observe(viewLifecycleOwner) {
                if (it != null) {
                    videoAdapter.submitList(it.toList())
                    Log.d("리스폰", categoryList.value.toString())
                }
            }
            pageToken.observe(viewLifecycleOwner) {
                if (it != null) {
                    Log.d("토큰", pageToken.value.toString())
                    //호출 횟수 테스트용 observe
                }
            }
            btnList.observe(viewLifecycleOwner) {
                if (it != null) {
                    btnsAdapter.submitList(it)
                }
            }

        }
    }

    private fun getNextPage() = with(viewModel) {
        viewModel.setNextPage()
    }


}