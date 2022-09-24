package com.saikiran.practiseapp.ui

import androidx.lifecycle.*
import com.saikiran.practiseapp.model.GitRepo
import com.saikiran.practiseapp.repository.MainRepository
import com.saikiran.practiseapp.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _dataState: MutableLiveData<DataState<List<GitRepo>>> = MutableLiveData()
    val dataState: LiveData<DataState<List<GitRepo>>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            if (mainStateEvent == MainStateEvent.GetReposEvents) {
                mainRepository.getRepos().onEach { dataState ->
                    _dataState.value = dataState
                }.launchIn(viewModelScope)
            }
        }
    }
}

sealed class MainStateEvent {
    object GetReposEvents : MainStateEvent()
}