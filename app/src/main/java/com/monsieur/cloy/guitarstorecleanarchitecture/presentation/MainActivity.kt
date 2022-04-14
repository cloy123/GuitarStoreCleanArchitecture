package com.monsieur.cloy.guitarstorecleanarchitecture.presentation

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import com.monsieur.cloy.guitarstorecleanarchitecture.R
import com.monsieur.cloy.guitarstorecleanarchitecture.databinding.ActivityMainBinding
import com.monsieur.cloy.guitarstorecleanarchitecture.presentation.main.StartFragment
import com.monsieur.cloy.guitarstorecleanarchitecture.presentation.viewModels.MainViewModel
import com.monsieur.cloy.guitarstorecleanarchitecture.utilits.APP_ACTIVITY
import com.monsieur.cloy.guitarstorecleanarchitecture.utilits.backButton
import com.monsieur.cloy.guitarstorecleanarchitecture.utilits.replaceFragment
import com.monsieur.cloy.guitarstorecleanarchitecture.utilits.toolbarMenu
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        APP_ACTIVITY = this
        replaceFragment(StartFragment(), false)
        setSupportActionBar(binding.toolbar)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val sharedPreferences = getSharedPreferences("theme", MODE_PRIVATE)
        if (menu != null) {
            if(sharedPreferences.getBoolean("dark", false)){
                val item = menu.getItem(0)
                item.icon = getDrawable(R.drawable.ic_round_light_mode_24)
            }
            toolbarMenu = menu
        }
        return super.onCreateOptionsMenu(menu)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun changeTheme(){
        val sharedPreferences = getSharedPreferences("theme", MODE_PRIVATE)
        if(sharedPreferences.getBoolean("dark", false)){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            sharedPreferences.edit().putBoolean("light", true).apply()
            sharedPreferences.edit().putBoolean("dark", false).apply()
        }
        else if(sharedPreferences.getBoolean("light", false)){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            sharedPreferences.edit().putBoolean("dark", true).apply()
            sharedPreferences.edit().putBoolean("light", false).apply()
        }
        else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            sharedPreferences.edit().putBoolean("light", false).apply()
            sharedPreferences.edit().putBoolean("dark", true).apply()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                backButton()
                true
            }
            R.id.action_mode -> {
                changeTheme()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}