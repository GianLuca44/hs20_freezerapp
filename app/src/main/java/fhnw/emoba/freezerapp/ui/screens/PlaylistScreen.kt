package fhnw.emoba.freezerapp.ui.screens

import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import fhnw.emoba.freezerapp.model.FreezerModel
import fhnw.emoba.freezerapp.model.Screen
import fhnw.emoba.freezerapp.ui.DefaultBody
import fhnw.emoba.freezerapp.ui.Drawer
import fhnw.emoba.freezerapp.ui.GoHomeFAB

@Composable
fun PlaylistScreen(model: FreezerModel){
    val scaffoldState = rememberScaffoldState()
    Scaffold(scaffoldState                = scaffoldState,
             topBar                       = {  },
             bodyContent                  = { DefaultBody(model, Screen.PLAYLISTS) },
             drawerContent                = { Drawer(model) },
             floatingActionButton         = { GoHomeFAB(model) },
             floatingActionButtonPosition = FabPosition.End,
             isFloatingActionButtonDocked = true
    )
}

//@Composable
//private fun AboutTopBar(scaffoldState: ScaffoldState) {
//    TopAppBar(title          = { Text(Screen.PLAYLISTS.title) },
//              navigationIcon = { DrawerIcon(scaffoldState) }
//    )
//}