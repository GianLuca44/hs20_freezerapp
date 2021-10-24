package fhnw.emoba.freezerapp.ui.screens

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import fhnw.emoba.freezerapp.data.RadioItems
import fhnw.emoba.freezerapp.model.FreezerModel
import fhnw.emoba.freezerapp.ui.Drawer

@Composable
fun RadioScreen(model: FreezerModel){
    val scaffoldState = rememberScaffoldState()

    Scaffold(scaffoldState = scaffoldState,
        topBar        = {  },
        bodyContent   = { RadioResults(model) },
        drawerContent = { Drawer(model) }
    )
}

@Composable
private fun RadioResults(model: FreezerModel){
    with(model) {
        if(isLoading){
            Box(Modifier.fillMaxWidth().fillMaxHeight(0.9f), alignment = Alignment.Center){
                CircularProgressIndicator()
            }
        }
        else {
            if (radioTracklist.isEmpty()) {
                Box(Modifier.fillMaxWidth().fillMaxHeight(0.9f), alignment = Alignment.Center){
                    Text("Keine Songs gefunden", style = MaterialTheme.typography.h6)
                }

            } else {
                LazyColumnFor(items = radioTracklist,
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(0.9f)) {
                    RadioView(it)
                }
            }
        }

    }
}

@Composable
private fun RadioView(radio: RadioItems){
    with(radio) {
        ListItem(text          = { Text(title) })
        Divider()
    }
}