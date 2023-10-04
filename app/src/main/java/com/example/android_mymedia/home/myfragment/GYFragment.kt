package com.example.android_mymedia.home.myfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android_mymedia.R
import com.example.android_mymedia.databinding.GYFragmentBinding


class GYFragment : Fragment() {

    private var _binding: GYFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = GYFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initView() = with(binding) {

    }

    //재생 목록을 받아올건데
    //음악 재생 목록을 받아올거고
    //저에 대한 프로필을 추가
    //굉장히 정신 없는 애니메이션을 마구잡이로 넣을 것



}