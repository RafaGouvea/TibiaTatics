package com.example.tibiatatics.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tibiatatics.R
import com.example.tibiatatics.remote.NewsModelWebClient
import com.example.tibiatatics.ui.adapter.HomeFragmentAdapter
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private var newsModelWebClient = NewsModelWebClient()
    private lateinit var adapter: HomeFragmentAdapter
    private lateinit var recyclerview: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycleView(view)
        lifecycleScope.launch{
            newsModelWebClient.loadFrom()?.let { adapter.updateNewsList(it) }
        }
    }

    private fun initRecycleView(view: View) {
        this.adapter = HomeFragmentAdapter()
        recyclerview = view.findViewById(R.id.activity_lista_noticias_recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        recyclerview.adapter = this.adapter
    }
}
