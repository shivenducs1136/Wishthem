package com.dsckiet.wishthem

import android.Manifest
import android.content.ContentValues.TAG
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.dsckiet.wishthem.databinding.FragmentOnFriendClickedBinding
import android.content.Intent
import android.graphics.Bitmap
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.net.Uri
import android.util.Log
import com.karumi.dexter.PermissionToken
import android.widget.Toast
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.PermissionRequest
import java.io.File
import android.media.MediaScannerConnection
import android.os.Environment
import android.view.LayoutInflater
import java.io.FileOutputStream
import java.io.IOException
import java.lang.Exception
import android.os.StrictMode
import android.os.StrictMode.VmPolicy

import android.widget.FrameLayout
import android.widget.RelativeLayout
import androidx.core.content.FileProvider
import android.provider.MediaStore
import java.io.ByteArrayOutputStream


class OnFriendClickedFragment : Fragment() {

    private lateinit var binding: FragmentOnFriendClickedBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_on_friend_clicked,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.OFCBackbtn.setOnClickListener {
            findNavController().navigate(R.id.action_onFriendClickedFragment_to_main2Fragment)
        }
        val builder = VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        var bundleid2 = this.arguments
        var namebundle : String = ""
        var taglinebundle : String = ""
        var dobbundle : String = ""
        var flag = "false"
        var img =""
        if(bundleid2 != null){

            namebundle = bundleid2.getString("Adnamebundle").toString()
            taglinebundle = bundleid2.getString("Adtaglinebundle").toString()
            dobbundle = bundleid2.getString("Addobbundle").toString()
            flag = bundleid2.getString("Adflag").toString()
            img = bundleid2.getString("Adimage").toString()
        }
        binding.card1Name.text = namebundle
        binding.card1Tagline.text = taglinebundle
        binding.OFCImagee.setImageURI(img.toUri())

        /*binding.OFCShareBtn.setOnClickListener {

            Dexter.withActivity(requireActivity())
                .withPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                        if (report.areAllPermissionsGranted()) {
                            val file: File? = saveBitMap(
                                requireContext(),
                                binding.sendRelLay
                            ) //which view you want to pass that view as parameter
                            Log.e("FILE", file.toString())

                            if (file != null) {
                                Log.i("TAG", "Drawing saved to the gallery!")
                                Toast.makeText(
                                    requireContext(),
                                    "Card Saved to Gallery!",
                                    Toast.LENGTH_SHORT
                                ).show()
                                val intent = Intent(Intent.ACTION_SEND)
                                intent.type = "image/*"
                                intent.putExtra(Intent.EXTRA_TEXT, "Demo Sharing Text")
                                intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file))
                                requireContext().startActivity(Intent.createChooser(intent, "Share Image"))
                            } else {
                                Log.i("TAG", "Oops! Image could not be saved.")
                                Toast.makeText(
                                    requireContext(),
                                    "Card could not be saved!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            Toast.makeText(
                                requireContext(),
                                "Permissions are not granted!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        permissions: List<PermissionRequest?>?,
                        token: PermissionToken
                    ) {
                        token.continuePermissionRequest()
                    }
                }).check()

        }*/


         */
        /*
        var images = listOf(
            R.layout.card1,
            R.drawable.ic_baseline_add_24,
            R.drawable.ic_baseline_arrow_back_24,
        )

        val adapter = FriendViewPager(images)
        binding.OFCViewPager.adapter = adapter

        TabLayoutMediator(binding.tablayout,binding.OFCViewPager, TabLayoutMediator.TabConfigurationStrategy({ tab, position->

        })).attach()
        binding.OFCViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
//                Snackbar.make(view,"You are selected"+{position+1},Snackbar.LENGTH_SHORT).show()
            }
        })*/ // View Pager Code

        binding.OFCShareBtn.setOnClickListener {
            val bitmap = Bitmap.createBitmap(binding.sendRelLay.width, binding.sendRelLay.height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            binding.sendRelLay.draw(canvas)
            val uri = getImageUri(requireContext(),bitmap)
            shareImageUri(uri)
        }
    }
    fun getImageUri(inContext: Context, inImage: Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path =
            MediaStore.Images.Media.insertImage(inContext.contentResolver, inImage, "Title", null)
        return Uri.parse(path)
    }

    private fun shareImageUri(uri: Uri?) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_STREAM, uri)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.type = "image/png"
        startActivity(intent)
    }

    private fun saveBitMap(context: Context, drawView: View): File? {
        val pictureFileDir = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
            "Wishme"
        ) // enter folder name to save image
        if (!pictureFileDir.exists()) {
            val isDirectoryCreated = pictureFileDir.mkdirs()
            if (!isDirectoryCreated) Log.i("ATG", "Can't create directory to save the image")
            return null
        }
        val filename = pictureFileDir.path + File.separator + System.currentTimeMillis() + ".jpg"
        val pictureFile = File(filename)
        val bitmap = getBitmapFromView(drawView)
        try {
            pictureFile.createNewFile()
            val oStream = FileOutputStream(pictureFile)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, oStream)
            oStream.flush()
            oStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
            Log.i("TAG", "There was an issue saving the image.")
        }
        scanGallery(context, pictureFile.absolutePath)
        return pictureFile
    }


    //create bitmap from view and returns it
    private fun getBitmapFromView(view: View): Bitmap {
        //Define a bitmap with the same size as the view
        val returnedBitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        //Bind a canvas to it
        val canvas = Canvas(returnedBitmap)
        //Get the view's background
        val bgDrawable = view.background
        if (bgDrawable != null) {
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas)
        } else {
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE)
        }
        // draw the view on the canvas
        view.draw(canvas)
        //return the bitmap
        return returnedBitmap
    }


    private fun scanGallery(cntx: Context, path: String) {
        try {
            MediaScannerConnection.scanFile(
                cntx, arrayOf(path), null
            ) { path, uri -> }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}