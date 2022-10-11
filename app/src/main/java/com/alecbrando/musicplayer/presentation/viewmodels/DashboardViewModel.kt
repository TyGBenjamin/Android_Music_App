package com.alecbrando.musicplayer.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.alecbrando.musicplayer.domains.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/** DashboardViewModel - start of application
 * @author Ngu Nguyen 10/10/2022
 */
@HiltViewModel
class DashboardViewModel @Inject constructor(private val repo: Repository): ViewModel() {

}