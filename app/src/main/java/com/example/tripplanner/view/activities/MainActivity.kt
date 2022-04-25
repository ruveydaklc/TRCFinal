package com.example.tripplanner.view.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tripplanner.R
import com.example.tripplanner.databinding.ActivityMainBinding
import com.example.tripplanner.databinding.TabLayoutBinding
import com.example.tripplanner.view.fragments.GezdiklerimFragment
import com.example.tripplanner.view.fragments.GezilecekFragment
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    lateinit var binding :ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //adding tabs
        binding.tabLayout.addTab(binding.tabLayout.newTab())
        binding.tabLayout.addTab(binding.tabLayout.newTab())

        tabOlustur()

        binding.tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tabSec(tab!!.position)
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        tabSec(0) //tekrar aynı fragment oluşmasını engelliyoruz

    }

    @SuppressLint("SetTextI18n")
    fun tabOlustur(){
        var tab= TabLayoutBinding.inflate(layoutInflater)
        tab.tvbaslik.text="Gezilecek"
        tab.ivTabIcon.setImageResource(R.drawable.gezilecekler_va)
        binding.tabLayout.getTabAt(0)!!.setCustomView(tab.root)

        tab = TabLayoutBinding.inflate(layoutInflater)
        tab.tvbaslik.text="Gezdiklerim"
        tab.ivTabIcon.setImageResource(R.drawable.gezdiklerim_va)
        binding.tabLayout.getTabAt(1)!!.setCustomView(tab.root)
    }

    fun tabSec(index:Int){
        val ft = supportFragmentManager.beginTransaction()
        when (index)
        {
            0 ->
                ft.replace(R.id.fragmentContainerView, GezilecekFragment())
            1 ->
                ft.replace(R.id.fragmentContainerView, GezdiklerimFragment())
        }
        ft.commit()
    }


}