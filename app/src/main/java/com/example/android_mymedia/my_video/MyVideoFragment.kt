package com.example.android_mymedia.my_video

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android_mymedia.databinding.MyVideoFragmentBinding


class MyVideoFragment : Fragment() {


    private var _binding: MyVideoFragmentBinding? = null
    private val binding get() = _binding!!
    private val myVideoAdapter by lazy {
        MyVideoAdapter()
    }
    private val viewModel by lazy {
        ViewModelProvider(this@MyVideoFragment)[MyVideoViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MyVideoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
    }

    private fun initView() = with(binding) {
        recyclerHorizon.adapter = myVideoAdapter
        recyclerHorizon.layoutManager = GridLayoutManager(context, 2)

    }

    private fun initViewModel() {
        with(viewModel) {

            liveBookMarkList.observe(viewLifecycleOwner) {
                myVideoAdapter.submitList(it.toList())

            }
        }

    }
}