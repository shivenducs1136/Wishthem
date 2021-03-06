package com.dsckiet.wishthem

import android.Manifest
import android.app.Activity.RESULT_OK
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.DialogInterface
import android.content.Intent
import android.icu.util.Calendar
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.dsckiet.wishthem.databinding.FragmentFriendFieldBinding
import androidx.annotation.RequiresApi
import com.google.android.material.snackbar.Snackbar


class FriendFieldFragment : Fragment() {

    private lateinit var binding: FragmentFriendFieldBinding
    private var date:String =""
    companion object{
        val IMAGE_REQUEST_CODE = 100
    }
    private var uri:Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_friend_field,container,false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.chooseBdayBtn.setOnClickListener{
            val c: Calendar = Calendar.getInstance()
            val mYear = c.get(Calendar.YEAR)
            val mMonth = c.get(Calendar.MONTH)
            val mDay = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { view, year, monthOfYear, dayOfMonth ->
                    binding.dateTxtView.setText(
                        String.format(
                            "%02d-%02d-%04d", dayOfMonth,
                            monthOfYear + 1, year
                        )
                    )
                    date = "$dayOfMonth-${monthOfYear +1}-$year"
                }, mYear, mMonth, mDay
            )

            datePickerDialog.show()
        }
        binding.selectImgBtn.setOnClickListener {
            pickimagefromgallery()
        }
        binding.submitBtn.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val tagline = binding.taglineEditText.text.toString()
            val flag = "true"
            val image = uri
            var bundle = Bundle()
                bundle.putString("namebundle",name)
                bundle.putString("taglinebundle",tagline)
                bundle.putString("dobbundle",date)
                bundle.putString("flag",flag)
                bundle.putString("image", image.toString())
            if(name.isNotEmpty() && tagline.isNotEmpty() && date.isNotEmpty()){
                findNavController().navigate(R.id.action_friendFieldFragment_to_main2Fragment,bundle)
                binding.nameEditText.text = null
                binding.taglineEditText.text = null
                uri = null

            }
            else{
                Snackbar.make(requireView(),"Please enter the required details",Snackbar.LENGTH_SHORT).show()
            }
        }

    }
    private fun pickimagefromgallery(){
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.type = "image/*"
        requireActivity().startActivityFromFragment(this,intent, IMAGE_REQUEST_CODE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK){
            binding.mainImg.setImageURI(data?.data)
            uri = data?.data
        }
    }



}
