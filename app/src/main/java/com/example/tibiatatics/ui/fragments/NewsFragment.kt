package com.example.tibiatatics.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tibiatatics.R
import com.example.tibiatatics.databinding.ActivityMainBinding
import com.example.tibiatatics.remote.NewsModelWebClient
import com.example.tibiatatics.ui.adapter.NewsFragmentAdapter
import kotlinx.coroutines.launch


class NewsFragment : Fragment() {

    private var newsModelWebClient = NewsModelWebClient()
    private lateinit var adapter: NewsFragmentAdapter
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
            newsModelWebClient.loadNewsFrom()?.let { adapter.updateNewsList(it) }
        }
    }

    private fun initRecycleView(view: View) {
        this.adapter = NewsFragmentAdapter{
            Toast.makeText(requireContext(), "teste", Toast.LENGTH_LONG).show()
        }
        recyclerview = view.findViewById(R.id.activity_lista_noticias_recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        recyclerview.adapter = this.adapter
    }
}
