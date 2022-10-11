package com.alecbrando.musicplayer.presentation.screens.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alecbrando.musicplayer.R



/**
 * A simple [Fragment] subclass.
 * Use the [FirstOnboard.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstOnboard : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first_onboard, container, false)
    }
}