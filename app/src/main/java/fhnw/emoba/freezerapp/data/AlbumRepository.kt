package fhnw.emoba.freezerapp.data

import android.content.Context
import java.io.InputStream
import java.nio.charset.StandardCharsets

object AlbumRepository {
//    lateinit var album: Album
    
    fun loadSquad(context: Context) {
//        val albumAsString = loadJSONFromAsset(context, "albumlist.json")
//
//        val al = JSONObject(albumAsString)
//        album = Album(JSONObject(albumAsString))
    }

//    const val baseURL = "https://api.deezer.com"
//    //const val type = "/album"
//    const val baseSearchURL = "search?q="
//
//
//    fun search(searchParam:String) : List<Track>{
//        var searchResult =  searchResultAPIRequest(searchParam)
//        var outputList = loadSongs(JSONObject(searchResult))
//
//        return outputList
//    }
//
//    private fun loadSongs(jsonObject: JSONObject) : List<Track> {
//        var data = jsonObject.optJSONArray("data")
//        var searchList = ArrayList<Track>()
//
//        for (x in 0 until data.length()){
//            var songModel = Track(data.getJSONObject(x))
//            searchList.add(songModel)
//        }
//
//        return searchList
//    }
//
//    private fun searchResultAPIRequest(searchParam: String): String {
//        var requestURL = URL("$baseURL/$baseSearchURL$searchParam")
//        return requestURL.readText()
//
//    }
    
    /**
     * Liest einen File, der in den Assets abgespeichert ist, ein und gibt den Inhalt als String zurueck
     */
    private fun loadJSONFromAsset(context: Context, fileName: String): String {
        val inputStream: InputStream = context.assets.open(fileName)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        
        return String(buffer, StandardCharsets.UTF_8)
    }
}