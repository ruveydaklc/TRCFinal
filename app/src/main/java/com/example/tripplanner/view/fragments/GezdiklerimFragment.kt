package com.example.tripplanner.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tripplanner.R
import com.example.tripplanner.databinding.FragmentGezdiklerimBinding

/** Gezdiklerim Liste Fragment (bknz. GezilecekFragment) */
class GezdiklerimFragment : Fragment() {
    lateinit var binding: FragmentGezdiklerimBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= FragmentGezdiklerimBinding.inflate(inflater)


        return binding.root
    }

}