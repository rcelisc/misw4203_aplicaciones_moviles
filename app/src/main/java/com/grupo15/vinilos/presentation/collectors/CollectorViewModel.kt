package com.grupo15.vinilos.presentation.collectors

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
class CollectorViewModel @Inject constructor(
    private val collectorRepository: CollectorRepository,
    @DispatchersModule.IODispatcher private val dispatcherIO: CoroutineDispatcher
) : ViewModel() {

    private val _collectors = MutableLiveData(emptyList<Collector>())
    val collectors: LiveData<List<Collector>> = _collectors

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getCollectors() {
        viewModelScope.launch(dispatcherIO) {
            val result = collectorRepository.getCollectors()
            if (result.isSuccess) {
                val collectors = result.getOrNull()
                if (!collectors.isNullOrEmpty())
                    _collectors.postValue(collectors)
            } else {
                val error = result.exceptionOrNull()
                if (error != null)
                    _error.postValue(error.message)
            }
        }
    }

}
