package com.dsckiet.wishthem

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dsckiet.wishthem.adapter.FriendsAdapter
import com.dsckiet.wishthem.databinding.FragmentMainBinding
import com.dsckiet.wishthem.entity.FriendsEntity
import com.dsckiet.wishthem.viewmodel.FriendViewModel

class MainFragment(val application: Application) : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return binding.root
    }
}