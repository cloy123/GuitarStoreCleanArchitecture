package com.monsieur.cloy.guitarstorecleanarchitecture.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.monsieur.cloy.guitarstorecleanarchitecture.databinding.FragmentStartBinding
import com.monsieur.cloy.guitarstorecleanarchitecture.presentation.catalog.CatalogFragment
import com.monsieur.cloy.guitarstorecleanarchitecture.presentation.main.basket.BasketFragment
import com.monsieur.cloy.guitarstorecleanarchitecture.presentation.main.history.HistoryFragment
import com.monsieur.cloy.guitarstorecleanarchitecture.presentation.viewModels.MainViewModel
import com.monsieur.cloy.guitarstorecleanarchitecture.utilits.changeToolBar
import com.monsieur.cloy.guitarstorecleanarchitecture.utilits.replaceFragment
import com.monsieur.cloy.guitarstorecleanarchitecture.utilits.showToast
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class StartFragment: Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        initFunk()
        return binding.root
    }

    private fun initFunk(){
        binding.inShopButton.setOnClickListener {
            replaceFragment(CatalogFragment())
        }
        binding.registration.setOnClickListener {
            replaceFragment(RegistrationFragment())
        }
        binding.login.setOnClickListener {
            replaceFragment(LoginFragment())
        }
        binding.out.setOnClickListener {
            viewModel.logout()
        }
        binding.history.setOnClickListener {
            replaceFragment(HistoryFragment())
        }
        binding.basket.setOnClickListener {
            if(viewModel.currentUser.value == null){
                replaceFragment(LoginFragment())
            }else{
                replaceFragment(BasketFragment())
            }
        }
        viewModel.currentUser.observe(viewLifecycleOwner, Observer {
            updateFields(it != null)
        })
    }

    override fun onStart() {
        super.onStart()
        changeToolBar(menu = true, homeButton = false, "")
    }

    private fun updateFields(isLogin: Boolean){
        if(!isLogin){
            binding.registration.visibility = View.VISIBLE
            binding.login.visibility = View.VISIBLE
            binding.out.visibility = View.INVISIBLE
            binding.history.visibility = View.INVISIBLE
            binding.basket.visibility = View.INVISIBLE
        }else{
            binding.registration.visibility = View.INVISIBLE
            binding.login.visibility = View.INVISIBLE
            binding.out.visibility = View.VISIBLE
            binding.history.visibility = View.VISIBLE
            binding.basket.visibility = View.VISIBLE
        }
    }
}