package com.monsieur.cloy.guitarstorecleanarchitecture.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.monsieur.cloy.guitarstorecleanarchitecture.databinding.FragmentLoginBinding
import com.monsieur.cloy.guitarstorecleanarchitecture.presentation.viewModels.MainViewModel
import com.monsieur.cloy.guitarstorecleanarchitecture.utilits.backButton
import com.monsieur.cloy.guitarstorecleanarchitecture.utilits.changeToolBar
import com.monsieur.cloy.guitarstorecleanarchitecture.utilits.replaceFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class LoginFragment: Fragment() {

    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        initFunk()
        return binding.root
    }

    private fun login(){
        val login = binding.login.text?.trim().toString()
        if(login.isEmpty()){
            binding.loginTextInput.error = "Введите логин"
            return
        }else{
            binding.loginTextInput.error = ""
        }
        val password = binding.password.text?.trim().toString()
        if(password.isEmpty()){
            binding.passwordTextInput.error = "Введите пароль"
            return
        }else{
            binding.passwordTextInput.error = ""
        }
        viewModel.login(login, password)
    }

    override fun onStart() {
        super.onStart()
        changeToolBar(menu = false, homeButton = true, "Войти")
        viewModel.currentUser.observe(viewLifecycleOwner, Observer{
            if(it == null){
                if(binding.login.text?.isNotEmpty() == true){
                    binding.tvInfo.text = "такой пользователь не найден или введён неверный пароль"
                    binding.tvInfo.visibility = View.VISIBLE
                }else{
                    binding.tvInfo.text = ""
                    binding.tvInfo.visibility = View.GONE
                }
            }else{
                //viewModel.currentUser.value = it
                backButton()
            }
        })
    }

    private fun initFunk(){
        binding.buttonLogin.setOnClickListener {
            login()
        }
        binding.buttonRegistration.setOnClickListener {
            replaceFragment(RegistrationFragment())
        }
    }
}