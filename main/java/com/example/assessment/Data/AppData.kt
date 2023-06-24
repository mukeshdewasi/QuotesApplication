package com.example.assessment.Data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
@Entity(tableName = "App Data")
@Parcelize
data class AppData(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var tittle: String,
    var image:String=""

):Parcelable
