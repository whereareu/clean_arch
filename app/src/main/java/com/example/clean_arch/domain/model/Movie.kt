package com.example.clean_arch.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(val id: Int, val description: String, val image: String, val title: String) :
    Parcelable {
}