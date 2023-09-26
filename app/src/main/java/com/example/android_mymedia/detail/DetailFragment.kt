package com.example.android_mymedia.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.android_mymedia.R
import com.example.android_mymedia.databinding.DetailFragmentBinding


class DetailFragment : Fragment() {

    private  var _binding : DetailFragmentBinding? = null
    private val binding get() = _binding!!

    private var isLiked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.detailBtnLike.setOnClickListener {
            if(isLiked) {

                binding.detailBtnLike.setBackgroundResource(R.drawable.confirm_button)
                binding.detailBtnLike.setText(R.string.button_like)
                Toast.makeText(requireContext(), "이 비디오가 좋아요 리스트에서 삭제되었습니다.", Toast.LENGTH_LONG).show()
            }
            else {
                binding.detailBtnLike.setBackgroundResource(R.drawable.clicked_confirm_button)
                binding.detailBtnLike.setText(R.string.button_unlike)
                Toast.makeText(requireContext(), "이 비디오가 좋아요 리스트에 추가되었습니다.", Toast.LENGTH_LONG).show()
            }
            isLiked = !isLiked
        }
    }
}