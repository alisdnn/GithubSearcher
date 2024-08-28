package com.alisdn.githubsearcher.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alisdn.githubsearcher.presentation.model.UserReposItem
import com.alisdn.githubsearcher.presentation.state.BadgeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {

    private val _sharedState = MutableStateFlow<BadgeState>(BadgeState.None)
    val sharedState = _sharedState.asStateFlow()

    private var isSumForksTriggered = false

    private var sumJob: Job? = null

    private var _totalForks = 0L
    private val totalForks: Long
        get() = _totalForks

    fun sumForks(repos: List<UserReposItem>, delay: Long = 0L) {
        if (isSumForksTriggered) return
        isSumForksTriggered = true
        _sharedState.update { BadgeState.Loading }
        sumJob = viewModelScope.launch(Dispatchers.Default) {
            _totalForks = 0
            repos.forEach { repo ->
                delay(delay)
                _totalForks += repo.forks ?: 0
                ensureActive()
            }
            _sharedState.update {
                BadgeState.Finished(totalForks)
            }
        }
    }

    fun stopCalculation() {
        sumJob?.cancel()
        isSumForksTriggered = false
        _sharedState.update {
            BadgeState.None
        }
    }

    override fun onCleared() {
        super.onCleared()
        stopCalculation()
    }
}