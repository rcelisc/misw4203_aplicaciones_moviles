package com.grupo15.vinilos.presentation.albums.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo15.vinilos.data.model.Album
import com.grupo15.vinilos.data.repository.album.AlbumRepository
import com.grupo15.vinilos.di.DispatchersModule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

@HiltViewModel
class AlbumDetailViewModel @Inject constructor(
    private val albumRepository: AlbumRepository,
    @DispatchersModule.IODispatcher private val dispatcherIO: CoroutineDispatcher
) : ViewModel() {

    private val _album = MutableLiveData<Album?>(null)
    val album: LiveData<Album?> = _album

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getAlbum(idAlbum: String) {
        viewModelScope.launch(dispatcherIO) {
            val result = albumRepository.getAlbum(idAlbum)
            if (result.isSuccess) {
                val album = result.getOrNull()
                _album.postValue(album)
            } else {
                val error = result.exceptionOrNull()
                if (error != null)
                    _error.postValue(error.message)
            }
        }
    }

}
