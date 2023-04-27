package com.grupo15.vinilos.presentation.performers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo15.vinilos.data.model.Performer
import com.grupo15.vinilos.data.repository.performer.PerformerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PerformerViewModel @Inject constructor(private val performerRepository: PerformerRepository) :
    ViewModel() {

    private val _performers = MutableLiveData(emptyList<Performer>())
    val performers: LiveData<List<Performer>> = _performers

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getPerformers() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = performerRepository.getPerformers()
            if (result.isSuccess) {
                val performers = result.getOrNull()
                if (!performers.isNullOrEmpty())
                    _performers.postValue(performers)
            } else {
                val error = result.exceptionOrNull()
                if (error != null)
                    _error.postValue(error.message)
            }
        }
    }
}
