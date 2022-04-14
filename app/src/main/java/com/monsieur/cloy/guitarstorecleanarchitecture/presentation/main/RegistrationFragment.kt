package com.monsieur.cloy.guitarstorecleanarchitecture.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.monsieur.cloy.domain.models.User
import com.monsieur.cloy.guitarstorecleanarchitecture.databinding.FragmentRegistrationBinding
import com.monsieur.cloy.guitarstorecleanarchitecture.presentation.viewModels.MainViewModel
import com.monsieur.cloy.guitarstorecleanarchitecture.utilits.backButton
import com.monsieur.cloy.guitarstorecleanarchitecture.utilits.changeToolBar
import com.monsieur.cloy.guitarstorecleanarchitecture.utilits.showToast
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class RegistrationFragment: Fragment() {

    private var _binding : FragmentRegistrationBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        initFunk()
        return binding.root
    }

    private fun registration(){
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
        val number = binding.number.text?.trim().toString()
        if(number.isEmpty()){
            binding.numberTextInput.error = "Введите номер"
            return
        }else{
            binding.numberTextInput.error = ""
        }
        val newUser = User(0, login, password, number)
        viewModel.registerUser(newUser)
    }

    override fun onStart() {
        super.onStart()
        changeToolBar(menu = false, homeButton = true, "Зарегистрироваться")
    }

    private fun initFunk(){
        viewModel.isUserRegistered.observe(viewLifecycleOwner, Observer {
            if(it != null && !it){
                if(binding.login.text?.isNotEmpty() == true){
                    binding.tvInfo.text = "такой пользователь уже существует"
                    binding.tvInfo.visibility = View.VISIBLE
                }
            }else if(it != null && it){
                if(binding.login.text?.isNotEmpty() == true){
                showToast("Пользователь зарегистрирован")
                backButton()
                }
            }
        })

        binding.buttonRegistration.setOnClickListener {
            registration()
        }
    }
}