package fhnw.emoba.freezerapp.data

import org.json.JSONArray
import org.json.JSONObject

//import

class Radio(json: JSONObject){
    //val id = json.getInt("id")


    var tracks = tracks(json.getJSONArray("data"))
    
    private fun tracks(memberArray: JSONArray) : List<RadioItems>{
        val list: MutableList<RadioItems> = mutableListOf()
        for (i in 0 until memberArray.length()) {
            list.add(RadioItems(memberArray.getJSONObject(i)))
        }
        return list
    }
    
    override fun toString(): String {
        return "Quad(squadName='', id='')"
        //"Quad(squadName='$name', homeTown='$artist', formed=$pictureUrl, secretBase='$preview', members=$members)"
    }
}

class RadioItems(json:JSONObject){
    var title = json.getString("title")



}