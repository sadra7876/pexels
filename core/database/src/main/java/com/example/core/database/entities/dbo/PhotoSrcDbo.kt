package com.example.core.database.entities.dbo

import kotlinx.serialization.Serializable


@Serializable
data class PhotoSrcDbo (
    val large: String,
    val original: String,
    val medium : String,
    val small : String,
    val tiny : String
)