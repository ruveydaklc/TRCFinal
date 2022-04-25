package com.example.tripplanner.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tripplanner.R
import com.example.tripplanner.databinding.FragmentGezilecekBinding


class GezilecekFragment : Fragment() {

    lateinit var binding:FragmentGezilecekBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding= FragmentGezilecekBinding.inflate(inflater)
        return binding.root
    }

}