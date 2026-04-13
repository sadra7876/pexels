package com.example.core.database.utils

import androidx.room.TypeConverter
import com.example.core.database.entities.dbo.PhotoSrcDbo
import kotlinx.serialization.json.Json

class Converters {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    @TypeConverter
    fun fromPhotoSrc(src: PhotoSrcDbo): String {
        return json.encodeToString(PhotoSrcDbo.serializer(), src)
    }

    @TypeConverter
    fun toPhotoSrc(jsonString: String): PhotoSrcDbo {
        return json.decodeFromString(PhotoSrcDbo.serializer(), jsonString)
    }
}