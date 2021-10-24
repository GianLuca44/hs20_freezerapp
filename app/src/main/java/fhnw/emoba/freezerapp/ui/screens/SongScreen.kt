package fhnw.emoba.freezerapp.ui.screens

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.onActive
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fhnw.emoba.freezerapp.data.Track
import fhnw.emoba.freezerapp.model.FreezerModel
import fhnw.emoba.freezerapp.model.Screen
import fhnw.emoba.freezerapp.ui.Drawer

@Composable
fun SongScreeen(model: FreezerModel) {
    val scaffoldState = rememberScaffoldState()
    
    Scaffold(scaffoldState = scaffoldState,
             topBar        = { HomeTopBar(model, scaffoldState) },
             bodyContent   = { SearchResults(model) },
             drawerContent = { Drawer(model) }
    )
}

@Composable
private fun SearchResults(model: FreezerModel){
    with(model) {
        if(isLoading){
            Box(Modifier.fillMaxWidth().fillMaxHeight(0.9f), alignment = Alignment.Center){
                CircularProgressIndicator()
            }
        }
        else {
            if (tracklist.isEmpty()) {
                Box(Modifier.fillMaxWidth().fillMaxHeight(0.9f), alignment = Alignment.Center){
                    Text("Keine Songs gefunden", style = MaterialTheme.typography.h6)
                }

            } else {
                LazyColumnFor(items = tracklist,
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(0.9f)) {

                    Button(onClick = { setSong(it) },backgroundColor = Color.Transparent) {
                        SongView(it)
                    }
                }
            }
        }

    }
}

@Composable
private fun SongView(city: Track){
    with(city) {
//        Button(onClick = m) {
            ListItem(text          = { Text(name) },
                secondaryText = { Text(artist) },
            )
            Divider()
//        }

    }
}

@Composable
fun LoadingBox(message: String){
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier            = Modifier.fillMaxSize()) {
        Text(message, style = MaterialTheme.typography.h5)
        CircularProgressIndicator(modifier = Modifier.padding(10.dp))
    }
}

@Composable
private fun HomeTopBar(model: FreezerModel, scaffoldState: ScaffoldState) {
    model.apply {
//        TopAppBar(
//                title          = { Text(Screen.SONGS.title) },
//                navigationIcon = {  },
//                actions        = { IconButton(onClick = { currentScreen = Screen.ALBUMS }) {
//                    Icon(Icons.Filled.ArrowForward)
//                }
//                }
//        )
    }
}

//@Preview
//@Composable
//fun Preview() {
//    HomeScreen(FreezerModel)
//}


