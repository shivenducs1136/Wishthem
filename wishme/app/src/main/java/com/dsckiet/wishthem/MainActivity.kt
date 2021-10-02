package com.dsckiet.wishthem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.dsckiet.wishthem.databinding.ActivityMainBinding
import com.dsckiet.wishthem.viewmodel.FriendViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
//    private lateinit var viewModel: FriendViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ViewDataBinding? =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        val navController = navHostFragment.navController
//
//        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory
//            .getInstance(application)).get(FriendViewModel::class.java)
    }
}