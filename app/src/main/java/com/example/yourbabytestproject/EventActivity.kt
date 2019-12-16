package com.example.yourbabytestproject

import android.os.Bundle
import android.util.Log
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.yourbabytestproject.databinding.ActivityEventBinding
import kotlinx.android.synthetic.main.activity_event.*


class EventActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEventBinding
    private lateinit var viewModel: EventViewModel
    private lateinit var videoUrl: String
    private lateinit var mediaController: MediaController
    private val VIDEO_SAMPLE = "https://developers.google.com/training/images/tacoma_narrows.mp4"




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)

        videoView.setVideoPath(VIDEO_SAMPLE)

        videoView.start()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_event)
        viewModel = ViewModelProviders.of(this).get(EventViewModel::class.java)
        viewModel.getEventInfo(intent.getIntExtra("id",1))

        actionBar?.title = "Event"
        supportActionBar?.title = "Event"

        viewModel.eventInfo.observe(this, Observer {
            it?.let {
                binding.fullDescription.text = it.fullDescription
                Log.i("Internet", it.fullDescription)
                videoUrl = it.videoUrl
            }
        })
        val imageId = intent.getIntExtra("imageId", 1)
        event_title.text = intent.getStringExtra("title")
        short_description.text = intent.getStringExtra("shortDescription")
        event_icon.setImageResource(
            when (imageId) {
                1 -> R.drawable.ic_u1
                2 -> R.drawable.ic_u2
                3 -> R.drawable.ic_u3
                4 -> R.drawable.ic_doctor
                5 -> R.drawable.ic_hypoderm_red
                6 -> R.drawable.ic_hypoderm_yellow
                else -> R.drawable.ic_doctor
            }
        )
        full_description.text = getString(R.string.long_lorem_ipsum)
    }


    override fun onPause() {
        super.onPause()
        videoView.pause()
    }

}
