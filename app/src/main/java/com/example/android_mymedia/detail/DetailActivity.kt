package com.example.android_mymedia.detail

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import coil.load
import com.example.android_mymedia.R
import com.example.android_mymedia.databinding.DetailActivityBinding


class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var binding: DetailActivityBinding

    private var isLiked = false

    private val data: DetailModel? by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_DATA, DetailModel::class.java)
        } else {
            intent.getParcelableExtra<DetailModel>(EXTRA_DATA)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()


    }

    private fun initView() = with(binding) {
        if (data == null) return@with
        val loadData = data as DetailModel

        detailIbBackButton.setOnClickListener {
            finish()
        }


        detailIvThumbnail.load(loadData.highImgUrl)

        detailTvTitle.text = loadData.title
        detailTvChannelName.text = loadData.channelTitle
        detailTvDescription.text = loadData.description




        binding.detailBtnLike.setOnClickListener {
            if (isLiked) {
                binding.detailBtnLike.setBackgroundResource(R.drawable.confirm_like_button)
                binding.detailBtnLike.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.ic_heart_drawable,
                    0
                )
                Toast.makeText(this@DetailActivity, "좋아요 리스트에서 삭제되었습니다.", Toast.LENGTH_LONG).show()
            } else {
                binding.detailBtnLike.setBackgroundResource(R.drawable.clicked_confirm_like_button)
                binding.detailBtnLike.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.ic_heart_filled_drawable,
                    0
                )
                Toast.makeText(this@DetailActivity, "좋아요 리스트에 추가되었습니다.", Toast.LENGTH_LONG).show()
            }
            isLiked = !isLiked
        }

        binding.detailBtnShare.setOnClickListener {
            shareUrl(loadData.videoUrl)
        }
    }

    private fun shareUrl(url: String) {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, url)
            type = "text/plain"
        }

        startActivity(Intent.createChooser(shareIntent, null))
    }
}