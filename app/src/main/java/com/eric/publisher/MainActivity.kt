package com.eric.publisher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.eric.publisher.data.TimeUtil
import com.eric.publisher.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class MainActivity : AppCompatActivity() {

    private val viewModel:MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val adapter = MainAdapter()
        binding.recyclerviewMain.adapter = adapter

        viewModel.allArticle.observe(this, androidx.lifecycle.Observer {
            it?.let{
                adapter.submitList(it)
            }
        })



    }
}
