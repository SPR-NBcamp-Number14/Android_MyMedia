package com.example.android_mymedia.home.fragment

import android.content.Intent
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
import com.example.android_mymedia.detail.DetailActivity
import com.example.android_mymedia.home.adapter.BtnsAdapter
import com.example.android_mymedia.home.viewmdoel.HomeViewModel
import com.example.android_mymedia.home.viewmdoel.HomeViewModelFactory
import com.example.android_mymedia.home.adapter.VideoAdapter
import com.example.android_mymedia.home.data.model.PlayListModel
import com.example.android_mymedia.home.viewmdoel.HomeClickEvent


class HomeFragment : Fragment() {

    private var _binding: HomeFragmentBinding? = null
    private var isMenuClicked: Boolean = false
    private val binding get() = _binding!!
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            HomeViewModelFactory()
        )[HomeViewModel::class.java]
    }

    private val videoAdapter by lazy {
        VideoAdapter(
            onItemClicked = { item ->
                toDetail(item)
            }
        )
    }

    private val btnsAdapter by lazy {
        BtnsAdapter(
            onClicked = { item ->

                reset()
                setCategory(item.category)
                binding.homeRvVideoList.scrollToPosition(0)

            }
        )
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

                if (isAtEndOfList) {

                    getNextPage() // 주석 시 홈 api 사용 x

                }
            }
        })

        homeIvBtnTop.setOnClickListener {
            homeRvVideoList.scrollToPosition(0)
        }
        homeIvBtnBottom.setOnClickListener {
            if (lastItemPosition() <= -1) return@setOnClickListener
            homeRvVideoList.scrollToPosition(lastItemPosition())
        }

    }

    private fun initViewModel() {
        with(viewModel) {
            categoryList.observe(viewLifecycleOwner) {
                if (it != null) {
                    videoAdapter.submitList(it.toList())
                    Log.d("리스폰", categoryList.value.toString())
                    if (it.toList().size == 10) {
                        binding.homeRvVideoList.scrollToPosition(0)
                    }
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
                    Log.d("버튼.뷰모델.리스트", it.toString())
                }
            }
            event.observe(viewLifecycleOwner) { event ->
                when (event) {
                    is HomeClickEvent.OpenDetail -> {
                        Intent(requireContext(), DetailActivity::class.java).apply {
                            putExtra(DetailActivity.EXTRA_DATA, event.item)
                            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        }.run { requireContext().startActivity(this) }
                    }

                }

            }
        }
    }

    private fun getNextPage() = with(viewModel) {
        setNextPage()
    }

    private fun setCategory(category: String) = with(viewModel) {
        setCategory(category)
    }

    private fun reset() = with(viewModel) {
        reset()
    }

    private fun toDetail(item: PlayListModel) = with(viewModel) {
        onClickItemForDetail(item)
    }

    private fun lastItemPosition(): Int = with(viewModel) {
        val size = categoryList.value?.size ?: 0
        return size - 2
    }

}