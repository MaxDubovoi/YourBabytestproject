package com.example.yourbabytestproject

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
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

        adapter.submitList(
            listOf(
                ListItem("icon", "1", 1),
                ListItem("icon", "2", 2),
                ListItem("icon", "3", 3),
                ListItem("icon", "4", 4),
                ListItem("icon", "5", 5),
                ListItem("icon", "6", 6)
            )
        )


        /*viewModel.items.observe(this, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })*/
    }
}
