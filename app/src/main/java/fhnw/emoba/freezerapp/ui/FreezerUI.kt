package fhnw.emoba.freezerapp.ui

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import fhnw.emoba.freezerapp.model.FreezerModel
import fhnw.emoba.freezerapp.model.Screen
import fhnw.emoba.freezerapp.model.Tabs
import fhnw.emoba.freezerapp.ui.screens.ArtistScreen
import fhnw.emoba.freezerapp.ui.screens.PlaylistScreen
import fhnw.emoba.freezerapp.ui.screens.RadioScreen
import fhnw.emoba.freezerapp.ui.screens.SongScreeen
import fhnw.emoba.modules.module04.weather_solution.ui.theme.FreezerAppTheme


@Composable
fun FreezerUI(model: FreezerModel) {
    FreezerAppTheme {
        Scaffold(topBar = { Bar(model) },
            bodyContent = { Body(model) },
            bottomBar = { PlayerPane(model = model) },
            drawerContent = { Drawer(model = model) }

        )
    }
}

@Composable
private fun Bar(model: FreezerModel) {
    model.apply {
        TopAppBar(title = { Text(title) })
    }
}

@Composable
private fun Body(model: FreezerModel) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(10.dp))  {
        TabRows(model = model)
        SearchForm(model)  // noch keine funktionalität

        when(model.selectedTab){
            Tabs.SONGS     -> { SongScreeen(model) }

            Tabs.ALBUMS -> { ArtistScreen(model) }
            Tabs.RADIO -> { RadioScreen(model) }
                    Tabs.PLAYLISTS     -> { PlaylistScreen(model) }
        }



    }
}

@Composable
fun PlayerPane (model: FreezerModel) {
    model.apply{
        var enabled = true
        Row(Modifier.background(color = MaterialTheme.colors.primarySurface).then(Modifier.fillMaxWidth(1f)).then(
            Modifier.height(80.dp))) {


            Column {
                // TODO: Frage:
                // throws NullPointer because of empty array
                // wie können Die Daten z.B. eines Albums sauber eingelesen werden.


                if (playerIsReady) {
                    IconButton(
                        onClick = { startPlayer() },
                        modifier = Modifier.background(
                            SolidColor(Color.LightGray),
                            shape = CircleShape,
                            alpha = if (enabled) 1.0f else 0.3f
                        )
                            .size(40.dp),
                        enabled = true
                    ) {
                        Icon(
                            Icons.Filled.PlayArrow,
                            tint = Color.Black,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                } else {
                    IconButton(
                        onClick = { pausePlayer() },
                        modifier = Modifier.background(Color.LightGray, shape = CircleShape)
                            .size(40.dp)
                    ) {
                        Icon(Icons.Filled.Pause, modifier = Modifier.size(24.dp))
                    }
                }

                if(tracklist.isEmpty()){
                    Text("Kein lied vorhanden")
                }
                else if( (counter > tracklist.size)  ){
                    Text("invalid selection")
                }
                else {
                    Text("Trackname: " + tracklist.get(counter).name)
                    Text("Artist: " + tracklist.get(counter).artist)
                }
//
           }





        }


    }
}



@Composable
private fun SearchForm(model: FreezerModel){
    model.apply {
        Card(Modifier.padding(20.dp)) {
            Column(modifier = Modifier.padding(10.dp)) {
                OutlinedTextField(value = searchQuery,
                    onValueChange = { text -> searchQuery = text },
                    imeAction = ImeAction.Done,
                    onImeActionPerformed = { action, softwareController ->
                        if (action == ImeAction.Done) {
                            softwareController?.hideSoftwareKeyboard()
                            search()
                        }
                    },
                    placeholder = { Text("Suche") },
                    keyboardType = KeyboardType.Text,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}



@Composable
fun TabRows(model: FreezerModel) {
    model.apply {
        Column {
            TabRow(selectedTabIndex = selectedTab.ordinal) {
                for(tab in Tabs.values()){
                    Tab(
                        text = { Text(tab.tabTitle)},
                        selected = tab == model.selectedTab,
                        onClick = {
                            model.selectedTab = tab}
                    )
                }
            }
        }
    }
}

//@Composable
//fun DefaultTopBar(model: FreezerModel, screen: Screen, lastScreen: Screen? = null, nextScreen: Screen? = null) {
//    model.apply {
//        TopAppBar(
//            title          = { Text(screen.title) },
//            navigationIcon = {  },
//            actions        = { if (nextScreen != null) {
//                IconButton(onClick = { currentScreen = nextScreen }) {
//                    Icon(Icons.Filled.ArrowForward)
//                }
//            }
//            }
//        )
//    }
//}

@Composable
fun DefaultScreen(model: FreezerModel, screen: Screen, lastScreen: Screen? = null, nextScreen: Screen? = null) {
    Scaffold(topBar                       = {  },
        bodyContent                  = { DefaultBody(model, screen) },
        floatingActionButton         = { GoHomeFAB(model) },
        floatingActionButtonPosition = FabPosition.End,
        isFloatingActionButtonDocked = true
    )
}

//@Composable
//fun DrawerIcon(scaffoldState: ScaffoldState){
//    IconButton(onClick = { scaffoldState.drawerState.open() }) {
//        Icon(Icons.Filled.Menu)
//    }
//}
//
//@Composable
//fun BackToScreenIcon(model: FreezerModel, screen: Screen) {
//    model.apply {
//        IconButton(onClick = { currentScreen = screen }) {
//            Icon(Icons.Filled.ArrowBack)
//        }
//    }
//}

@Composable
fun DefaultBody(model: FreezerModel, screen: Screen) {
    Box(
        modifier            = Modifier.fillMaxSize(),
    ) {
        Column {
//            ImageLoadInBackground( resId = R.drawable.deezerlogo)

            Text(text = screen.title)


            //ImageLoadInBackground(screen.resId)
        }
    }
}

@Composable
fun GoHomeFAB(model: FreezerModel) {
    model.apply {
        FloatingActionButton(
            onClick = {
                currentScreen = Screen.SONGS
            },
            icon    = { Icon(Icons.Filled.Home) })
    }
}

