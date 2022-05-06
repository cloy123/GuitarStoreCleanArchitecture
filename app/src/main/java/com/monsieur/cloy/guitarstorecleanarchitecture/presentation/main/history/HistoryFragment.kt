package com.monsieur.cloy.guitarstorecleanarchitecture.presentation.main.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.monsieur.cloy.domain.models.History
import com.monsieur.cloy.guitarstorecleanarchitecture.databinding.FragmentHistoryBinding
import com.monsieur.cloy.guitarstorecleanarchitecture.presentation.viewModels.MainViewModel
import com.monsieur.cloy.guitarstorecleanarchitecture.utilits.changeToolBar
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HistoryFragment: Fragment() {
    var _binding: FragmentHistoryBinding? = null
    val binding get() = _binding!!
    private val viewModel: MainViewModel by sharedViewModel()

    lateinit var recyclerAdapter: HistoryRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        initFunk()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        recyclerAdapter.setItems(null)
    }

    private fun initFunk() {
        initRecyclerAdapter()
        viewModel.findHistory()
        viewModel.history.observe(viewLifecycleOwner, Observer { list ->
            if(recyclerAdapter.historyItems == null){
                recyclerAdapter.setItems(list as ArrayList<History>)
            }
        })
    }

    private fun initRecyclerAdapter() {
        recyclerAdapter = HistoryRecyclerAdapter(requireContext())
        binding.historyRecycler.adapter = recyclerAdapter
    }


    override fun onStart() {
        super.onStart()
        changeToolBar(menu = false, homeButton = true, "История покупок")
    }
}