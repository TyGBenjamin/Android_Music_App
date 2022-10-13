package com.alecbrando.musicplayer.presentation.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alecbrando.musicplayer.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Onboarding : Fragment() {

//    companion object {
//        fun newInstance() = Onboarding()
//    }
//
//    private lateinit var viewModel: OnboardingViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_onboarding, container, false)

        val fragmentList = arrayListOf<Fragment>(


        )


        return view
    }



}