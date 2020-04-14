package com.company.myartist.model

data class Media(
    val media_id: String?,
    val type: String?,
    val user_id: String?,
    val caption: String?,
    val data: Data?,
    val create_date: String?,
    val use_type: String?,
    val uri: String?,
    val base_url: String?,
    val _extended: String?
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
        val version: String?,
        val version_big: String?,
        val version_orig: String?,
        val sizes: Size?,
        val x: String?,
        val y: String?,
        val ratio: String?,
        val ext: String?,
        val is_animated: String?
    )

    data class Size(
        val orig: SizeOrig?
    )

    data class SizeOrig(
        val x: String?,
        val y: String?,
        val hash_file_name: String?
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