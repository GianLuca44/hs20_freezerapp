package fhnw.emoba.freezerapp.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.FailedResource
import androidx.compose.ui.res.LoadedResource
import androidx.compose.ui.res.PendingResource
import androidx.compose.ui.res.loadImageResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import fhnw.emoba.R
import fhnw.emoba.freezerapp.model.FreezerModel
import fhnw.emoba.freezerapp.model.Screen

@Composable
fun Drawer(model: FreezerModel) {
    Column {
//        DrawerRow(model, Screen.SONGS)
//        DrawerRow(model, Screen.ALBUMS)
//        DrawerRow(model, Screen.RADIO)
//        DrawerRow(model, Screen.PLAYLISTS)
        ImageLoadInBackground(resId = R.drawable.deezerlogo, size= 300.dp)
    }
}

@Composable
fun DrawerRow(model: FreezerModel, screen: Screen) {
    model.apply {
        Row(verticalAlignment     = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            modifier              = Modifier.fillMaxWidth()){

            Text(screen.title,
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(5.dp)
                    .fillMaxWidth()
                    .clickable(onClick = { currentScreen = screen }))
        }
        Divider()
    }
}

@Composable
private fun ImageLoadInBackground(@DrawableRes resId: Int, size: Dp? = null) {
    val deferredResource = loadImageResource(id = resId)
    val resource         = deferredResource.resource

    when (resource) {
        is LoadedResource -> {
            val imageAsset = resource.resource!!
            if(size == null){
                Image(asset        = imageAsset,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .drawShadow(elevation = 4.dp,
                            shape = RoundedCornerShape(10.dp)
                        )
                )
            }
            else {
                Image(asset    = imageAsset,
                    modifier = Modifier
                        .size(size)
                        .padding(5.dp)
                        .drawShadow(elevation = 2.dp,
                            shape = RoundedCornerShape(4.dp)
                        )
                )
            }
        }
        is FailedResource -> {
            Text("failed", style = MaterialTheme.typography.h6, color = MaterialTheme.colors.error)
        }
        is PendingResource -> {
            Text(if(size == null) "loading ..." else "...", style = MaterialTheme.typography.h6)
        }
    }
}