package com.company.myartist.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Media(
    @PrimaryKey
    val media_id: String = "",
    val type: String? = null,
    val user_id: String? = null,
    val caption: String? = null,
    @Embedded
    val data: Data? = null,
    val create_date: String? = null,
    val use_type: String? = null,
    val uri: String? = null,
    val base_url: String? = null,
    val _extended: String? = null
) {
    companion object {
        val sizes = arrayOf(
            0,
            10,
            20,
            40,
            80,
            100,
            200,
            400,
            800,
            1000,
            1200,
            1400,
            1800,
            2000,
            2200,
            2400,
            2800
        )
    }

    fun makeUrl(ratio: MediaRatio, side: MediaSide, imageViewSize: Int): String {
        val size = getCorrectSizeImage(imageViewSize)
        val version = if (size > 1500) data?.version_big ?: "" else data?.version ?: ""
        return "${base_url}/img/${ratio}${side}${size}/${use_type}/${version}/${media_id}.${data?.ext}"
    }

    private fun getCorrectSizeImage(sizeImage: Int): Int {
        for (i in 0..sizes.size - 1) {
            if (i < sizes.size - 1 && sizeImage >= sizes[i] && sizeImage <= sizes[i + 1]) {
                return sizes[i]
            }
        }
        return sizes.size - 1
    }

    data class Data(
        val version: String? = null,
        val version_big: String? = null,
        val version_orig: String? = null,
        @Embedded
        val sizes: Size? = null,
        val x: String? = null,
        val y: String? = null,
        val ratio: String? = null,
        val ext: String? = null,
        @SerializedName("is_animated")
        val isanimated: String? = null
    )

    data class Size(
        @Embedded
        val orig: SizeOrig? = null
    )

    data class SizeOrig(
        @SerializedName("x")
        val x_: String? = null,
        @SerializedName("y")
        val y_: String? = null,
        val hash_file_name: String? = null
    )

    enum class MediaRatio {
        o,  //-соотношение сторон оригинального изображения
        s,  //-соотношение 1:1 (квадрат)
        h,  //-соотношение 3:2 (ландшафт)
        v   //-соотношение 3:5 (портрет)
    }

    enum class MediaSide {
        x, // по ширине
        y  // по высоте
    }
}