package com.alisdn.githubsearcher.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.alisdn.githubsearcher.domain.model.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RepoDetailViewModel
@Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

}