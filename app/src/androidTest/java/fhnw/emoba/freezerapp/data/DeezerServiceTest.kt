package fhnw.emoba.freezerapp.data

import fhnw.emoba.freezerapp.data.impl.RemoteDeezerService
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DeezerServiceTest {
    lateinit var appContext: Context

    @Before
    fun setup() {
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @Test
    fun testTrackRequest() {
        //given
        val deezerService = RemoteDeezerService()

        //when
        val bligg    = deezerService.requestTrack(true, "bligg")
        val eminem = deezerService.requestTrack(true, "eminem")

        //then
        assertEquals("Bligg", bligg.get(0).artist)
        assertEquals("Eminem", eminem.get(0).artist)
    }
}