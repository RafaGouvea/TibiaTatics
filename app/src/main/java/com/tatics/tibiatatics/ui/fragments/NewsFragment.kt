package com.tatics.tibiatatics.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tatics.tibiatatics.R
import com.tatics.tibiatatics.model.models.NewsModel
import com.tatics.tibiatatics.remote.WebClient
import com.tatics.tibiatatics.ui.adapter.NewsFragmentAdapter
import kotlinx.coroutines.launch


class NewsFragment : Fragment() {

    private var newsModelWebClient = WebClient()
    private lateinit var newsFragmentAdapter: NewsFragmentAdapter
    private lateinit var recyclerview: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycleView(view)
        lifecycleScope.launch {
            newsModelWebClient.loadNewsFrom()
                ?.let {
                    newsFragmentAdapter.updateNewsList(it)
                }
        }

    }

    private fun initRecycleView(view: View) {
        this.newsFragmentAdapter = NewsFragmentAdapter {
            val id = it.id
            val bundle = Bundle()
            bundle.putString("id", id.toString())

            val navController = findNavController()
            val currentDestinationId = navController.currentDestination?.id
            val isOnMenuHome = currentDestinationId == R.id.menu_news
            if (isOnMenuHome) {
                navController.navigate(R.id.action_menu_news_to_newsDetailFragment, bundle)
            } else {
                navController.navigate(R.id.menu_news)
            }
        }
        recyclerview = view.findViewById(R.id.activity_lista_noticias_recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        recyclerview.adapter = this.newsFragmentAdapter
    }
}
