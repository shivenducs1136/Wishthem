package com.dsckiet.wishthem

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.DialogInterface
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.dsckiet.wishthem.databinding.FragmentFriendFieldBinding
import com.google.android.material.datepicker.MaterialDatePicker
import android.widget.DatePicker
import androidx.annotation.RequiresApi


class FriendFieldFragment : Fragment() {

    private lateinit var binding: FragmentFriendFieldBinding
    private var date:String =""
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
                }, mYear, mMonth, mDay
            )
            date = "$mDay-$mMonth-$mYear"
            datePickerDialog.show()
        }
        binding.submitBtn.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val tagline = binding.taglineEditText.text.toString()
            val flag = "true"
            var bundle = Bundle()
            bundle.putString("namebundle",name)
            bundle.putString("taglinebundle",tagline)
            bundle.putString("dobbundle",date)
            bundle.putString("flag",flag)
            findNavController().navigate(R.id.action_friendFieldFragment_to_main2Fragment,bundle)
        }
    }
}
