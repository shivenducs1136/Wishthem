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
import com.dsckiet.wishthem.databinding.FragmentMain2Binding
import com.dsckiet.wishthem.entity.FriendsEntity
import com.dsckiet.wishthem.viewmodel.FriendViewModel

class Main2Fragment: Fragment() {

    private lateinit var binding : FragmentMain2Binding
    private lateinit var viewModel: FriendViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_main2, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainFab.setOnClickListener {
            findNavController().navigate(R.id.action_main2Fragment_to_friendFieldFragment)
        }
        val adapter = FriendsAdapter(requireContext(),this)
        val recyclerView = binding.mainRecview
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(FriendViewModel::class.java)
        viewModel.allFriends.observe(viewLifecycleOwner, Observer {list ->

            list?.let{
                adapter.updateFriendsList(it)
            }
        })


        binding.mainFab.setOnClickListener {
            findNavController().navigate(R.id.action_main2Fragment_to_friendFieldFragment)
        }
        var bundleid = this.arguments
        var namebundle : String = ""
        var taglinebundle : String = ""
        var dobbundle : String = ""
        var flag = "false"
        if(bundleid != null){

            namebundle = bundleid.getString("namebundle").toString()
            taglinebundle = bundleid.getString("taglinebundle").toString()
            dobbundle = bundleid.getString("dobbundle").toString()
            flag = bundleid.getString("flag").toString()
        }
        if(flag == "true"){
            if(namebundle.isNotEmpty() && taglinebundle.isNotEmpty() && dobbundle.isNotEmpty() ){
                viewModel.insertFriend(FriendsEntity(namebundle,taglinebundle,dobbundle))
                Toast.makeText(requireContext(),"$namebundle added", Toast.LENGTH_LONG).show()

            }
        }


    }

    fun onItemClicked(friendsEntity: FriendsEntity) {

        viewModel.deleteFriend(friendsEntity)
        Toast.makeText(requireContext(),"Deleted",Toast.LENGTH_LONG).show()

    }
}