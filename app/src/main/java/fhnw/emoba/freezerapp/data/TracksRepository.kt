package fhnw.emoba.freezerapp.data

import android.content.Context
import org.json.JSONObject
import java.io.InputStream
import java.nio.charset.StandardCharsets
import java.util.*

object TracksRepository {
    lateinit var album: Album
    lateinit var data: List<Album>

    const val baseURL = "https://api.deezer.com"
    //const val type = "/album"
    const val baseSearchURL = "search?q="

    
    fun loadSquad(context: Context) {
        //val squadAsString = loadJSONFromAsset(context, "chartlist.json")

        //val al = JSONObject(squadAsString)
        //album = Album(JSONObject(squadAsString))

    }

    fun search(searchParam:String) : List<Track>{
        var searchResult =  searchResultAPIRequest(searchParam)
        var outputList = loadSongs(JSONObject(searchResult))

        return outputList
    }

    private fun loadSongs(jsonObject: JSONObject) : List<Track> {
        var data = jsonObject.getJSONArray("data")
        var searchList = ArrayList<Track>()

        for (x in 0 until data.length()){
            var songModel = Track(data.getJSONObject(x))
            searchList.add(songModel)
        }

        return searchList
    }

    private fun searchResultAPIRequest(searchParam: String): String {
        try{

            var requestURL = "$baseURL/$baseSearchURL$searchParam"
            return content(requestURL)
        }
        catch (e:Exception){
//            val a= "eminem"
            return "$baseURL/$baseSearchURL"
        }


    }


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