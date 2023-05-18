package com.grupo15.vinilos.presentation.albums.detail.tracks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo15.vinilos.data.model.Track
import com.grupo15.vinilos.data.repository.album.AlbumRepository
import com.grupo15.vinilos.di.DispatchersModule
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

@HiltViewModel
class TrackViewModel @Inject constructor(
    private val albumRepository: AlbumRepository,
    @DispatchersModule.IODispatcher private val dispatcherIO: CoroutineDispatcher
) : ViewModel() {

    private val _tracks = MutableLiveData<List<Track>>()
    val tracks: LiveData<List<Track>> = _tracks

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun setTrackToAlbum(albumId: Int, nameTrack: String, duration: String) {
        viewModelScope.launch(dispatcherIO) {
            val newTrack = Track(name = nameTrack, duration = duration)
            val result = albumRepository.setTrackToAlbum(id = albumId, track = newTrack)
            if (result.isSuccess) {
                val album = result.getOrNull()
                _tracks.postValue(album?.album?.tracks)
            } else {
                val error = result.exceptionOrNull()
                if (error != null)
                    _error.postValue(error.message)
            }
        }
    }

}