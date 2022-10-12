package com.alecbrando.musicplayer.presentation.intro

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.alecbrando.musicplayer.R
import com.alecbrando.musicplayer.databinding.FragmentIntroPagesBinding
import com.alecbrando.musicplayer.presentation.dashboard.DashboardActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class IntroThirdFragment @Inject constructor(
    private val moveToDashboard: () -> Unit
) : Fragment() {
    private var _binding: FragmentIntroPagesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentIntroPagesBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
    }

    private fun initListeners() = with(binding) {
        btnExplore.setOnClickListener { moveToDashboard() }
    }

    private fun initViews() = with(binding) {
        animationIntroScreen.setAnimation(R.raw.like)
        tvIntro.text = "Let's get started! Chose a song to listen to."

        btnExplore.setBackgroundColor(Color.parseColor("#FF6200EE"))
        btnExplore.setAnimation(R.raw.rocket)
    }
}