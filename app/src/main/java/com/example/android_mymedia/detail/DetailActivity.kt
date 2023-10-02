package com.example.android_mymedia.detail

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import coil.load
import com.example.android_mymedia.R
import com.example.android_mymedia.databinding.DetailActivityBinding
import com.example.android_mymedia.room.VideoDatabase
import com.example.android_mymedia.room.VideoEntity
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
        val db = VideoDatabase.getInstance(applicationContext)

        binding = DetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (data == null) return

        val loadData = data as DetailModel

        CoroutineScope(Dispatchers.IO).launch {
            val videoId = db?.VideoDAO()?.getVideoById(data!!.id)

            withContext(Dispatchers.Main) {
                if (videoId!!.isNotEmpty()) {
                    binding.detailBtnLike.setBackgroundResource(R.drawable.clicked_confirm_like_button)
                    binding.detailBtnLike.setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.ic_heart_filled_drawable,
                        0
                    )
                    isLiked = true
                } else {
                    binding.detailBtnLike.setBackgroundResource(R.drawable.confirm_like_button)
                    binding.detailBtnLike.setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.ic_heart_drawable,
                        0
                    )
                    isLiked = false
                }
            }
        }

        val videoInfo = VideoEntity(
            data!!.id,
            data!!.videoUrl,
            data!!.highImgUrl,
            data!!.title,
            data!!.channelTitle,
            data!!.description
        )

        Log.d("videoInfo", "${videoInfo}")

        binding.detailIbBackButton.setOnClickListener {
            finish()
        }


        lifecycle.addObserver(binding.detailYoutubeplayer)
        binding.detailYoutubeplayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val youtubeVideo = data!!.id
                youTubePlayer.cueVideo(youtubeVideo, 0F)
            }
        })

        binding.detailTvTitle.text = loadData.title
        binding.detailTvChannelName.text = loadData.channelTitle
        binding.detailTvDescription.text = loadData.description




        binding.detailBtnLike.setOnClickListener {
            if (isLiked) {
                binding.detailBtnLike.setBackgroundResource(R.drawable.confirm_like_button)
                binding.detailBtnLike.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.ic_heart_drawable,
                    0
                )
                CoroutineScope(Dispatchers.IO).launch {
                    db!!.VideoDAO().deleteVideoById(data!!.id)
                }
                Toast.makeText(this, "좋아요 리스트에서 삭제되었습니다.", Toast.LENGTH_LONG).show()
            } else {
                binding.detailBtnLike.setBackgroundResource(R.drawable.clicked_confirm_like_button)
                binding.detailBtnLike.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.ic_heart_filled_drawable,
                    0
                )
                CoroutineScope(Dispatchers.IO).launch {
                    db!!.VideoDAO().insertVideo(videoInfo)
                }
                Toast.makeText(this, "좋아요 리스트에 추가되었습니다.", Toast.LENGTH_LONG).show()
            }
            isLiked = !isLiked
        }

        binding.detailBtnShare.setOnClickListener {
            shareUrl(data!!.videoUrl)
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