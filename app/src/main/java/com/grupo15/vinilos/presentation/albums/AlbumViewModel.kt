package com.grupo15.vinilos.presentation.albums

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo15.vinilos.data.model.Album
import com.grupo15.vinilos.data.repository.album.AlbumRepository
import com.grupo15.vinilos.di.DispatchersModule.IODispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val albumRepository: AlbumRepository,
    @IODispatcher private val dispatcherIO: CoroutineDispatcher
) : ViewModel() {

    private val _albums = MutableLiveData(emptyList<Album>())
    val albums: LiveData<List<Album>> = _albums

    private val _albumCreated = MutableLiveData<Album>()
    val albumCreated: LiveData<Album> = _albumCreated

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getAlbums() {
        viewModelScope.launch(dispatcherIO) {
            val result = albumRepository.getAlbums()
            if (result.isSuccess) {
                val albums = result.getOrNull()
                if (!albums.isNullOrEmpty()) _albums.postValue(albums)
            } else {
                val error = result.exceptionOrNull()
                if (error != null) _error.postValue(error.message)
            }
        }
    }

    fun createAlbum(
        name: String,
        cover: String,
        releaseDate: String,
        description: String,
        genre: String,
        recordLabel: String
    ) {
        viewModelScope.launch(dispatcherIO) {
            val dateFormat = SimpleDateFormat("dd/mm/yyyy", Locale.getDefault())
            val date: Date = dateFormat.parse(releaseDate) as Date
            val newAlbum = Album(
                name = name,
                cover = cover,
                releaseDate = date,
                description = description,
                genre = genre,
                recordLabel = recordLabel
            )
            val result = albumRepository.createAlbum(newAlbum)
            if (result.isSuccess) {
                val album = result.getOrNull()
                album?.let {
                    _albumCreated.postValue(it)
                }

            } else {
                val error = result.exceptionOrNull()
                if (error != null) _error.postValue(error.message)
            }
        }
    }
}
