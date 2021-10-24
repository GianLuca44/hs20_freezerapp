package fhnw.emoba.freezerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import fhnw.emoba.EmobaApp
import fhnw.emoba.freezerapp.data.impl.RemoteDeezerService
import fhnw.emoba.freezerapp.model.FreezerModel
import fhnw.emoba.freezerapp.ui.FreezerUI

object FreezerApp : EmobaApp {
    lateinit var model: FreezerModel
    
    override fun initialize(activity: ComponentActivity, savedInstanceState: Bundle?) {
        //AlbumRepository.loadSquad(activity)
        //RadioRepository.loadSquad(activity) NOT YET WORKING
        val deezerService = RemoteDeezerService()

        model = FreezerModel(deezerService)
        model.getForecastsOfFavoritesAsync()
        model.getFavouriteRadioAsync()
        model.getFavouriteArtistAsync()
        //model.getRadiosAsync()
        //model.requestFirstSong()  // load Song from Deezer
    }
    
    @Composable
    override fun createAppUI() {
        FreezerUI(model)
    }
}