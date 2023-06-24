package com.example.assessment.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.assessment.Data.AppData
@Dao
interface UserDao {
        @Insert
        fun insertUser(appdata:AppData)
        @Query("select * from 'App Data'")
        fun getAllRecords():MutableList<AppData>
        @Delete
        fun deleteRecords(appdata: AppData)
        @Query("select * from 'App Data' where id=:uid")
        fun getUser(uid:Int):AppData

}