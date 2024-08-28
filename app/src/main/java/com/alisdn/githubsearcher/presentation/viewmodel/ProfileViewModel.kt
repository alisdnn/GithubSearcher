package com.alisdn.githubsearcher.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.alisdn.githubsearcher.domain.model.ProfileRepository
import javax.inject.Inject

class ProfileViewModel
@Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

}