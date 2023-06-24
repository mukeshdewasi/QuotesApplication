package com.example.assessment.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.assessment.DAO.UserDao
import com.example.assessment.Data.AppData

@Database(entities = [AppData::class], version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun userDao():UserDao
}