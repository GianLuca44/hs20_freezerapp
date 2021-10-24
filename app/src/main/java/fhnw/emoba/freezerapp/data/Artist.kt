package fhnw.emoba.freezerapp.data

import org.json.JSONObject


class Artist(json: JSONObject){
    val name        = json.getString("name")
    val tracklist       = json.getString("tracklist") // to save the list of tracks
    val picture       = json.getString("picture_medium") // to save the list of tracks



//    val ab = json.getJSONObject("tracks")
//    val abc = json.getJSONObject("tracks").getJSONArray("data")

    //val xpz = tracks(json.getJSONObject("tracks").getJSONArray("data"))


//    val tracks = tracks(json.getJSONObject("tracks").getJSONArray("data"))
//
//    private fun tracks(memberArray: JSONArray) : List<Track>{
//        val list: MutableList<Track> = mutableListOf()
//        for (i in 0 until memberArray.length()) {
//            list.add(Track(memberArray.getJSONObject(i)))
//        }
//        return list
//    }
    
    override fun toString(): String {
        return "Quad(squadName='$name', homeTown='$tracklist')"
        //"Quad(squadName='$name', homeTown='$artist', formed=$pictureUrl, secretBase='$preview', members=$members)"
    }
}