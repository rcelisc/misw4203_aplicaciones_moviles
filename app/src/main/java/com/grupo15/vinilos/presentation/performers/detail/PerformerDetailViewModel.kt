package com.grupo15.vinilos.presentation.performers.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo15.vinilos.data.model.Performer
import com.grupo15.vinilos.data.repository.performer.PerformerRepository
import com.grupo15.vinilos.di.DispatchersModule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

@HiltViewModel
class PerformerDetailViewModel @Inject constructor(
    private val performerRepository: PerformerRepository,
    @DispatchersModule.IODispatcher private val dispatcherIO: CoroutineDispatcher
) : ViewModel() {

    private val _performer = MutableLiveData<Performer?>(null)
    val performer: LiveData<Performer?> = _performer

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getPerformer(idPerformer: Int) {
        viewModelScope.launch(dispatcherIO) {
            val result = performerRepository.getPerformer(idPerformer)
            if (result.isSuccess) {
                val performer = result.getOrNull()
                _performer.postValue(performer)
            } else {
                val error = result.exceptionOrNull()
                if (error != null)
                    _error.postValue(error.message)
            }
        }
    }

}
