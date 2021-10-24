package fhnw.emoba.freezerapp.data.impl

import android.content.Context
import fhnw.emoba.freezerapp.data.*
import org.json.JSONObject

class LocalDeezerService(val context: Context) : DeezerService {

        override fun requestTrack(query: String): Track = Track(JSONObject(content("track.json", context)))
        override fun requestTrack(searchActive: Boolean, query: String): MutableList<Track> {
                TODO("Not yet implemented")
        }

        override fun requestArtist(query: String): List<Artist> = listOf( Artist(JSONObject(content("album.json", context))) )

        override fun requestRadio(query: String): List<RadioItems> = listOf( RadioItems(JSONObject(content("radio.json", context))) )


}
