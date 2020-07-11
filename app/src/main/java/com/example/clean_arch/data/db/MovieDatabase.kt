package com.example.clean_arch.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.clean_arch.data.model.local.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}