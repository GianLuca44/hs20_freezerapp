package fhnw.emoba.freezerapp.data

import org.json.JSONObject
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class TrackTest {

    @Test
    fun testConstructor() {
        //given
        val json = JSONObject(
            "{" +
                    "\"title\": \"Rosalie\"," +
                    "\"preview\": \"https://api.deezer.com/xxx.mp3\"," +
                    "\"artist\": {" +
                    "\"name\": \"Bligg\"" +
                    "}" +
                    "}"
        )

        //when
        val rosalie = Track(json)
        //then
        rosalie.apply {
            assertEquals("Rosalie", name)
            assertEquals("https://api.deezer.com/xxx.mp3", preview)
            assertEquals("Bligg", artist)
        }
    }

}