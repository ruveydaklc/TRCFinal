package com.example.tripplanner.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tripplanner.R
import com.example.tripplanner.databinding.FragmentYerEkleBinding

/** Gezilecek Yer Ekleme Fragment*/
class YerEkleFragment : Fragment() {

    lateinit var binding: FragmentYerEkleBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= FragmentYerEkleBinding.inflate(inflater)





        return binding.root

    }





}