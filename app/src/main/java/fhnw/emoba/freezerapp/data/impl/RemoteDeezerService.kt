package fhnw.emoba.freezerapp.data.impl

import fhnw.emoba.freezerapp.data.*
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL

class RemoteDeezerService : DeezerService {
    val baseURL = "https://api.deezer.com"
    //const val type = "/album"
    val baseSearchURL = "search?q="

    var defJson = JSONObject("{" +
            "\"title\": \" \"," +
            "\"preview\": \" \"," +
            "\"artist\": {" +
            "\"name\": \" \"" +
            "}" +
            "}")

    var defaultArtist = JSONObject("{" +
            "\"name\": \" \"," +
            "\"tracklist\": \" \"," +
            "\"picture\":  \" \"" +
            "}")


    override fun requestTrack(query:String): Track {




        try {
            val url = URL("https://api.deezer.com/track/454634232")

            println(content(url.toString()))
            return Track(JSONObject(content(url.toString())))
            //return Track(JSONObject())
        }
        catch (e: Exception) {
            //todo: Handle exception correctly


            return Track(defJson )
        }
    }

    override fun requestTrack(searchActive:Boolean, query:String): List<Track> {

        try {
            val url = URL("https://api.deezer.com/search/track?q=$query")

            println(content(url.toString()))
            val json = JSONObject( content(url.toString()) )

            val arry = JSONObject(content(url.toString())).getJSONArray("data")
            // Frage:
            val listeee = powers(arry)


            //arry.get(0).has

            return listeee
            //return Track(JSONObject())
        }
        catch (e: Exception) {
            //todo: Handle exception correctly
            return listOf( Track(defJson) )
        }
    }

    override fun requestArtist(query: String): List<Artist> {

        try {
            val url = URL("https://api.deezer.com/search/artist?q=$query")

            println(content(url.toString()))
            val json = JSONObject( content(url.toString()) )

            val arry = JSONObject(content(url.toString())).getJSONArray("data")

            val listeee = generateArtistList(arry)



            return listeee
            //return Artist(JSONObject())
        }
        catch (e: Exception) {
            // Handle exception correctly
            return listOf( Artist(defaultArtist) )
        }
    }

    override fun requestRadio(query: String): List<RadioItems> {

        try {
            val url = URL("$baseURL/radio")

            val jsonString = JSONObject(content(url.toString()))
            val dataArr = jsonString.getJSONArray("data")
            val lst:MutableList<RadioItems> = mutableListOf()
            for ( i in 0 until dataArr.length()){
                lst.add( RadioItems(dataArr.getJSONObject(i)) )
            }
            return lst

        }
        catch (e: Exception) {
            //todo: Handle exception correctly
            return listOf(RadioItems(
                JSONObject("{" +
                        "\"title\": \" \"" +
                        "}"
                )
            ))
        }
    }

    // Hilfsfunktion um eine Liste von Tracks zu erstellen
    //TODO: gibt momentan eine Exception
    //org.json.JSONException: No value for title
    //        at org.json.JSONObject.get(JSONObject.java:400)
    //        at org.json.JSONObject.getString(JSONObject.java:561)
    //        at fhnw.emoba.freezerapp.data.Track.<init>(Track.kt:7)
    fun powers(powerArray: JSONArray): List<Track> {
        val list: MutableList<Track> = mutableListOf()
        for (i in 0 until powerArray.length()) {
            list.add(Track( powerArray.getJSONObject(i) ) )
        }
        return list
    }

    fun generateArtistList(artistArray: JSONArray): List<Artist> {
        val artistList: MutableList<Artist> = mutableListOf()
        for (i in 0 until artistArray.length()) {
            artistList.add(Artist( artistArray.getJSONObject(i) ) )
        }
        return artistList
    }
}