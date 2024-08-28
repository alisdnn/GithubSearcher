package com.alisdn.githubsearcher.presentation.viewmodel

import android.util.Log
import androidx.compose.animation.core.MutableTransitionState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alisdn.githubsearcher.domain.repository.ProfileRepository
import com.alisdn.githubsearcher.domain.repository.SearchRepository
import com.alisdn.githubsearcher.domain.model.UserModel
import com.alisdn.githubsearcher.domain.model.UserReposModel
import com.alisdn.githubsearcher.presentation.model.UserItem
import com.alisdn.githubsearcher.presentation.model.UserReposItem
import com.alisdn.githubsearcher.presentation.model.toUserItem
import com.alisdn.githubsearcher.presentation.model.toUserReposItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
    private val profileRepository: ProfileRepository
) : ViewModel() {

    private val _userState = MutableStateFlow(UserItem(userName = "", avatarUrl = ""))
    val userState = _userState.asStateFlow()

    private val _isImageSectionVisible = MutableStateFlow(MutableTransitionState(false))
    val isImageSectionVisible = _isImageSectionVisible.asStateFlow()

    private val _userRepoState = MutableStateFlow(emptyList<UserReposItem>())
    val userRepoState = _userRepoState.asStateFlow()

    private val _isReposVisible = MutableStateFlow(MutableTransitionState(false))
    val isReposVisible = _isReposVisible.asStateFlow()

    fun onSearchUser(userName: String) {
        if (userName.isBlank()) return
        viewModelScope.launch {
            try {
                searchRepository.getUserRepos(userName).let {
                    onSuccessResponse(it)
                }
            }catch (e:Exception){
                onErrorResponse(e)
            }

            try {
                profileRepository.searchUserDetail(userName).let {
                    onSuccessResponse(it)
                }
            }catch (e:Exception){
                onErrorResponse(e)
            }
        }
    }

    private fun onSuccessResponse(users: UserModel) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                _userState.value = users.toUserItem()
            }
            _isImageSectionVisible.value.targetState = true
        }
    }

    private fun onSuccessResponse(repos: List<UserReposModel>) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                _userRepoState.value = repos.map { it.toUserReposItem() }
            }
            delay(500L)
            _isReposVisible.value.targetState = true
        }
    }

    private fun onErrorResponse(error: Exception) {
        //handle error based on the project.
        Log.d("TAG", "onSearchUser: $error")

    }

    fun clearStates(){
        _userRepoState.value = emptyList()
        _userState.value = UserItem(null,"")
    }
}