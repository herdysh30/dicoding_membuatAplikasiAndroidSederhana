package com.example.submissionaplikasiandroidsederhana

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Manhwa(
    val name: String,
    val description: String,
    val photo: String,
    val author: String,
    val genre: String,
    val published: String
) : Parcelable

