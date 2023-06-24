package com.example.assessment

import java.text.SimpleDateFormat

class Util {
    companion object{
            fun getDataFromMills(milli:Long):String{
                var dateFormat=SimpleDateFormat("dd-MM-yyyy")
                return dateFormat.format(milli)
            }
    }
}