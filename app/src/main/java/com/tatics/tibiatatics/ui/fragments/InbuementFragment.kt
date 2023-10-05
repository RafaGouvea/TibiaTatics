package com.tatics.tibiatatics.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tatics.tibiatatics.R
import com.tatics.tibiatatics.model.InbuementDataSource
import com.tatics.tibiatatics.ui.adapter.InbuementsFragmentAdapter

class InbuementFragment : Fragment(), MenuProvider {

    private lateinit var inbuementsFragmentAdapter: InbuementsFragmentAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_inbuement, container, false)
        val menuHost : MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        toolbar(view)
        initRecycleView(view)
        addDataSource()

        return view
    }

    private fun toolbar(view: View) {
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar_inbuements)
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            it.setOnClickListener { findNavController() }
            requireActivity().onBackPressed()
        }
    }

    private fun addDataSource() {
        val dataSource = InbuementDataSource.createInbuementData()
        inbuementsFragmentAdapter.updateList(dataSource)
    }

    private fun initRecycleView(view: View) {

        this.inbuementsFragmentAdapter = InbuementsFragmentAdapter()
        recyclerView = view.findViewById(R.id.inbuements_list_recycleview)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = this.inbuementsFragmentAdapter
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_inbuement_filter, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        val dataSource = InbuementDataSource.createInbuementData()
        recyclerView.smoothScrollToPosition(0)
        return when (menuItem.itemId) {
            R.id.menu_search_suporte -> {
                inbuementsFragmentAdapter.filterSuporte(dataSource)
                true
            }
            R.id.menu_search_aumento_de_skill -> {
                inbuementsFragmentAdapter.filterAumentoSkill(dataSource)

                true
            }
            R.id.menu_search_dano_elemental -> {
                inbuementsFragmentAdapter.filterDanoElemental(dataSource)
                true
            }
            R.id.menu_search_protecao_elemental -> {
                inbuementsFragmentAdapter.filterProtecaoElemental(dataSource)
                true
            }
            R.id.menu_search_remover_filtro -> {
                inbuementsFragmentAdapter.updateList(dataSource)
                true
            }
            else -> true
        }
    }
}
