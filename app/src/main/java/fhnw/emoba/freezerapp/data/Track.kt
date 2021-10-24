package fhnw.emoba.freezerapp.data

import androidx.compose.runtime.mutableStateOf
import org.json.JSONArray
import org.json.JSONObject

class Track(json: JSONObject){
    val name        = json.getString("title")
    val artist       = json.getJSONObject("artist").getString("name")
    //val pictureUrl =    json.getString("cover_medium")
    val preview =       json.getString("preview")
    val favourite = mutableStateOf(false)
    
//    private fun powers(powerArray: JSONArray) : List<String>{
//        val list: MutableList<String> = mutableListOf()
//        for (i in 0 until powerArray.length()) {
//            list.add(powerArray.getString(i))
//        }
//        return list
//    }
    
    override fun toString(): String {
        return "Track(name='', age=, secretIdentity='', )"
    }
}

class Tracklist(json: JSONObject){


    private fun tracks(trackArray: JSONArray) : List<String>{
    val list: MutableList<String> = mutableListOf()
    for (i in 0 until trackArray.length()) {
        list.add(trackArray.getString(i))
    }
    return list
    }
}