package com.alisdn.githubsearcher.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.alisdn.githubsearcher.domain.model.SearchRepository
import javax.inject.Inject

class SearchViewModel
@Inject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {

}