package fhnw.emoba.freezerapp.model


import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import fhnw.emoba.freezerapp.data.Artist
import fhnw.emoba.freezerapp.data.DeezerService
import fhnw.emoba.freezerapp.data.RadioItems
import fhnw.emoba.freezerapp.data.Track
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.util.Collections.emptyList

class FreezerModel(val deezerService: DeezerService) {
    val title = "The Album App"

    //lateinit var firstSong: List<Track>
    //lateinit var radioTracklist: List<RadioItems>

    var searchQuery by mutableStateOf("")

    var selectedTab by mutableStateOf(Tabs.SONGS)

//    val album: Album
//    get() = repo.album
//
//    val radio: Radio
//    get() = radioRepo.radio








    private val modelScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    var isLoading by mutableStateOf(false)

    //TODO: Korrekte deklaration einer Liste für Radio, Album Elemente?
    var tracklist: List<Track> by mutableStateOf(emptyList())


    var currentScreen by mutableStateOf(Screen.SONGS)

    fun search(){

            //Tabs.SONGS -> {

        modelScope.launch {
            //searchQuery = "bligg"

            if(searchQuery == "" ) {
            }
            else{
                tracklist = deezerService.requestTrack(true, searchQuery)
                artistList = deezerService.requestArtist(searchQuery)
                radioTracklist = deezerService.requestRadio(searchQuery)

                currentlyPlaying = tracklist.get(0).preview
            }
        }

            //}
            //else -> Unit
//            Tabs.ALBUMS -> {
//
//            }
//            Tabs.RADIO -> {
//                //
//            }
//            Tabs.PLAYLISTS -> Unit


    }

    //var favoriteCities = listOf( 0,0,0 )


    fun getForecastsOfFavoritesAsync() {
        isLoading = true

        modelScope.launch {
            tracklist = deezerService.requestTrack(true, "bligg")
            //firstSong.addAll( )
            currentlyPlaying = tracklist.get(0).preview
            isLoading = false

            //forecasts.forEach { loadWeatherIconsAsync(it) }
        }
    }

    var radioTracklist:List<RadioItems> by mutableStateOf(emptyList())

    fun getFavouriteRadioAsync() {
        isLoading = true

        modelScope.launch {
            radioTracklist = deezerService.requestRadio( "bligg")
            //firstSong.addAll( )
            isLoading = false

            //forecasts.forEach { loadWeatherIconsAsync(it) }
        }
    }

    var artistList:List<Artist> by mutableStateOf(emptyList())

    fun getFavouriteArtistAsync() {
        isLoading = true

        modelScope.launch {
            artistList = deezerService.requestArtist( "eminem")
            //firstSong.addAll( )
            isLoading = false

            //forecasts.forEach { loadWeatherIconsAsync(it) }
        }
    }

//    var radioTracklist: List<Radio> by mutableStateOf(emptyList())
//    fun getRadiosAsync() {
//        isLoading = true
//        radioTracklist = emptyList()
//        modelScope.launch {
//            radioTracklist =  listOf(deezerService.requestRadio("") )
//            isLoading = false
//
//            //forecasts.forEach { loadWeatherIconsAsync(it) }
//        }
//    }




//    var currentTracklist = listOf(Album(
//
//        ))

//    fun getTracksOfFavoritesAsync() {
//        isLoading = true
//
//        modelScope.launch {
////            currentTracklist = listOf(Track(
////
////            ))
//
//            isLoading = false
//
//            //forecasts.forEach { loadWeatherIconsAsync(it) }
//        }
//    }




//    MUSIC PLAYER   MUSIC PLAYER




    var playerIsReady by mutableStateOf(true)

    var counter by mutableStateOf(0)


    private var currentlyPlaying = ""  // wird nur intern gebraucht, soll kein Recompose ausloesen, daher auch kein MutableState

    fun setSong(track: Track) {
        counter = tracklist.indexOf(track)
        currentlyPlaying = track.preview
    }

    private val player = MediaPlayer().apply {
        setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
        )
        setOnPreparedListener {
            start()
        }
        setOnCompletionListener { playerIsReady = true }
    }

    fun startPlayer(){
        playerIsReady = false
        try {
            if (currentlyPlaying == tracklist.get(0).preview && !player.isPlaying) {
                player.start()
            } else {
                //currentlyPlaying = firstSong.get(0).preview
                player.reset()
                player.setDataSource(currentlyPlaying)
                player.prepareAsync()
            }
        } catch (e: Exception) {
            playerIsReady = true
        }
    }

    fun pausePlayer() {
        player.pause()
        playerIsReady = true
    }

    fun nextSong(){
        ++counter
    }
    fun toggleFavourite(){
        var currentTrack= tracklist.get(counter)
        tracklist.get(counter).favourite != currentTrack.favourite
    }

    fun getFavourite() : Boolean{
        return tracklist.get(counter).favourite.value
    }

    fun prevSong(){
        --counter
    }



}