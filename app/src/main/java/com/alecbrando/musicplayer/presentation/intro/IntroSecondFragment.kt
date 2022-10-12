package com.alecbrando.musicplayer.presentation.intro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.alecbrando.musicplayer.R
import com.alecbrando.musicplayer.databinding.FragmentIntroPagesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IntroSecondFragment : Fragment() {
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
    }

    private fun initViews() = with(binding) {
        animationIntroScreen.setAnimation(R.raw.electric_guitar)
        tvIntro.text = "♫ . ♫ ♫ . . ♫ ♫ ♫ . . ♫ ♫ . ♫ "
    }
}