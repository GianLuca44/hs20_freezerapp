package fhnw.emoba.freezerapp.ui.screens

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import fhnw.emoba.freezerapp.data.Artist
import fhnw.emoba.freezerapp.model.FreezerModel
import fhnw.emoba.freezerapp.ui.Drawer


@Composable
fun ArtistScreen(model: FreezerModel){
    val scaffoldState = rememberScaffoldState()

    Scaffold(scaffoldState = scaffoldState,
        topBar        = {  },
        bodyContent   = { ArtistList(model) },
        drawerContent = { Drawer(model) }
    )
}

@Composable
private fun ArtistList(model: FreezerModel){
    with(model) {
        if(isLoading){
            Box(Modifier.fillMaxWidth().fillMaxHeight(0.9f), alignment = Alignment.Center){
                CircularProgressIndicator()
            }
        }
        else {
            if (artistList.isEmpty()) {
                Box(Modifier.fillMaxWidth().fillMaxHeight(0.9f), alignment = Alignment.Center){
                    Text("Keine Songs gefunden", style = MaterialTheme.typography.h6)
                }

            } else {
                LazyColumnFor(items = artistList,
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(0.9f)) {
                    ArtistView(it)
                }
            }
        }

    }
}

@Composable
private fun ArtistView(artist: Artist){
    with(artist) {
        ListItem(text          = { Text(name) })
        Divider()
    }
}