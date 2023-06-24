package com.example.assessment.Data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuotesData(
    var id : Int,
    var Quotes :String,
    var color:Int
):Parcelable
