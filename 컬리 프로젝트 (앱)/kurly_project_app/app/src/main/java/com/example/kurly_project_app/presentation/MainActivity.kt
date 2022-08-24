package com.example.kurly_project_app.presentation

import com.example.kurly_project_app.R
import com.example.kurly_project_app.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupJetpackNavigation()


    }

    private fun setupJetpackNavigation() {
        val host = supportFragmentManager.findFragmentById(R.id.g_soha_nav_host_fragment) as NavHostFragment? ?: return
        navController = host.navController
        binding.bottomNavi.setupWithNavController(navController)
    }

    fun HideBottomNavi(state : Boolean){
        if(state) binding.bottomNavi.visibility = View.GONE else binding.bottomNavi.visibility = View.VISIBLE
    }

}