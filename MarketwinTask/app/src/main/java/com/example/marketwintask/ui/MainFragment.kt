package com.example.marketwintask.ui

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.ContentResolver
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.service.media.MediaBrowserService
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.marketwintask.R
import com.example.marketwintask.databinding.FragmentMainBinding
import java.io.FileNotFoundException
import java.io.InputStream


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private var imagereq = 21
    private lateinit var bitmap: Bitmap
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_main,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.selectImgBtn.setOnClickListener{


            val i = Intent()
            i.type = "image/*"
            i.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(i , imagereq)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == imagereq && resultCode == RESULT_OK) {
            bitmap= data?.extras?.get("image/*") as Bitmap
            binding.mySelectedImg.setImageBitmap(bitmap)
        }
    }
}