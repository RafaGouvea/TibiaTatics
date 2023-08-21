package com.example.tibiatatics.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.tibiatatics.R
import com.example.tibiatatics.ui.adapter.InbuementsFragmentAdapter
import com.example.tibiatatics.ui.adapter.NewsFragmentAdapter

class InbuementFragment : Fragment() {

    private lateinit var inbuementsFragmentAdapter: InbuementsFragmentAdapter
    private lateinit var recyclerview: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_inbuement, container, false)

        initRecycleView(view)

        return view
    }

    private fun initRecycleView(view: View) {

        this.inbuementsFragmentAdapter = InbuementsFragmentAdapter()
        recyclerview = view.findViewById(R.id.inbuements_list_recycleview)
        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        recyclerview.adapter = this.inbuementsFragmentAdapter
    }
}
