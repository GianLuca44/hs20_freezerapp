@file:JvmName("TrackServiceKt")

package fhnw.emoba.freezerapp.data

import android.graphics.Bitmap

val DEFAULT_ICON = Bitmap.createBitmap(
    120,
    120,
    Bitmap.Config.ALPHA_8)

interface DeezerService {
    fun requestTrack(query:String): Track
    fun requestTrack(searchActive: Boolean, query:String): List<Track>
    fun requestArtist(query:String): List<Artist>
    fun requestRadio(query:String): List<RadioItems>


    //fun requestWeatherIcon(icon: String, size: Int = 1): Bitmap
}
