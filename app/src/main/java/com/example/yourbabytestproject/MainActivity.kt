package com.example.yourbabytestproject

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.yourbabytestproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val adapter = ListEventAdapter(ListEventAdapter.ItemClickListener { eventId ->
            Log.i("Recycler", "CLICK!!!!")
        })
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.list.adapter = adapter
        actionBar?.title = "List"
        supportActionBar?.title = "List"
        Log.i("Main", "Called ViewModelProviders.of")
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)

        viewModel.property.observe(this, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
    }
}
