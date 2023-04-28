package com.grupo15.vinilos.presentation.albumes

import android.provider.MediaStore.Audio.Artists.Albums
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo15.vinilos.data.model.Album
import com.grupo15.vinilos.data.model.Collector
import com.grupo15.vinilos.data.repository.album.AlbumRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(private val albumRepository: AlbumRepository) :
    ViewModel() {

    private val _albums = MutableLiveData(emptyList<Album>())
    val albumes: LiveData<List<Album>> = _albums

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getAlbums() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = albumRepository.getAlbums()
            if (result.isSuccess){
                val albums = result.getOrNull()
                if (!albums.isNullOrEmpty())
                    _albums.postValue(albums)
            } else {
                val error = result.exceptionOrNull()
                if (error != null)
                    _error.postValue(error.message)
            }
        }
    }
}
