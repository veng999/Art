package com.Illarionov.art.model

import com.company.myartist.model.response.EventsResponse
import com.google.gson.Gson
import org.junit.Assert
import org.junit.Test
import java.io.FileReader

class EventTest {

    private val events = Gson().fromJson(
        FileReader(ClassLoader.getSystemClassLoader().getResource("eventsResponce.json").path),
        EventsResponse::class.java
    ).data?.events

    @Test
    fun getFormatDate() {
        val expected = "7.01"
        val actual = events?.get(0)?.getFormatDate(1588084119)
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun getBeforeLastIdEvent() {
        val expected = 4125249.toLong()
        val actual = events?.get(0)?.getBeforeLastIdEvent()
        Assert.assertEquals(expected, actual)
    }
}