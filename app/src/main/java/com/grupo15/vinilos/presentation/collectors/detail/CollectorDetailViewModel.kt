package com.grupo15.vinilos.presentation.collectors.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo15.vinilos.data.model.Collector
import com.grupo15.vinilos.data.repository.collector.CollectorRepository
import com.grupo15.vinilos.di.DispatchersModule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

@HiltViewModel
class CollectorDetailViewModel @Inject constructor(
    private val collectorRepository: CollectorRepository,
    @DispatchersModule.IODispatcher private val dispatcherIO: CoroutineDispatcher
) : ViewModel() {

    private val _collector = MutableLiveData<Collector?>(null)
    val collector: LiveData<Collector?> = _collector

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getCollector(idCollector: String) {
        viewModelScope.launch(dispatcherIO) {
            val result = collectorRepository.getCollector(idCollector)
            if (result.isSuccess) {
                val collector = result.getOrNull()
                _collector.postValue(collector)
            } else {
                val error = result.exceptionOrNull()
                if (error != null)
                    _error.postValue(error.message)
            }
        }
    }

}
