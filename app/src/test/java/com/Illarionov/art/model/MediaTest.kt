package com.Illarionov.art.model

import com.company.myartist.model.Media
import com.company.myartist.model.response.EventsResponse
import com.google.gson.Gson
import org.junit.Assert
import org.junit.Test
import java.io.FileReader

class MediaTest {

    private val media = Gson().fromJson(
        FileReader(ClassLoader.getSystemClassLoader().getResource("eventsResponce.json").path),
        EventsResponse::class.java
    ).data?.media

    @Test
    fun makeUrl() {
        val expected = "https://artchive.ru/res/media/img/ox0/topic_note/a9c/778596.jpg"
        val actual = media?.get(0)?.makeUrl(Media.MediaRatio.o, Media.MediaSide.x, 9)
        Assert.assertEquals(expected, actual)
    }
}