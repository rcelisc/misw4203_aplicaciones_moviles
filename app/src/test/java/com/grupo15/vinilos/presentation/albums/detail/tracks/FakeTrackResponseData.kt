package com.grupo15.vinilos.presentation.albums.detail.tracks

import com.grupo15.vinilos.data.model.SetTrackResponse
import com.grupo15.vinilos.presentation.albums.getFakeAlbum

fun getFakeTrackResponse(i: Int): SetTrackResponse {
    val album = getFakeAlbum(i)
    return SetTrackResponse(
        id = i,
        name = "Track Name $i",
        duration = "12:12:$i",
        album = album
    )
}
