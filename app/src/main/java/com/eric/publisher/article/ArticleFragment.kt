package com.eric.publisher.article

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.eric.publisher.R
import com.eric.publisher.databinding.FragmentArticleBinding


/**
 * A simple [Fragment] subclass.
 */
class ArticleFragment : Fragment() {

    private val viewModel: ArticleViewModel by lazy {
        ViewModelProvider(this).get(ArticleViewModel ::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentArticleBinding.inflate(inflater, container,
            false)

        val adapter = ArticleAdapter()
        binding.recyclerviewArticle.adapter = adapter

        viewModel.allArticle.observe(viewLifecycleOwner, Observer {
            it?.let{
                adapter.submitList(it)
            }
        })

        //跳轉post dialog
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_articleFragment_to_postDialog)
        }

        return binding.root
    }

}
