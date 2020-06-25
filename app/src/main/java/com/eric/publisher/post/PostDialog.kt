package com.eric.publisher.post

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.eric.publisher.R
import com.eric.publisher.databinding.DialogPostBinding

/**
 * A simple [Fragment] subclass.
 */
class PostDialog : AppCompatDialogFragment() {

    private val viewModel: PostViewModel by lazy {
        ViewModelProvider(this).get(PostViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DialogPostBinding.inflate(inflater, container,false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.buttonPost.setOnClickListener {
            viewModel.addData()
            Toast.makeText(context, "成功發佈文章", Toast.LENGTH_SHORT).show()
            Handler(Looper.getMainLooper()).postDelayed(Runnable{
                findNavController().navigate(R.id.action_postDialog_to_articleFragment) },2000)
        }

        return binding.root
    }

}
