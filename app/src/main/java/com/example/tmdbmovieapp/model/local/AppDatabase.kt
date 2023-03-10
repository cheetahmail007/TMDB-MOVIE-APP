package com.example.tmdbmovieapp.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tmdbmovieapp.model.local.data.*

@Database(
    entities = [
        Movie::class,
        MovieDetail::class,
        MovieGenre::class,
        ProductionCompany::class,
        ProductionCountry::class,
        SpokenLanguage::class
    ], version = 1, exportSchema = false
)

@androidx.room.TypeConverters(TypeConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getMovieDao(): MovieDao
    abstract fun getMoviesDao(): MoviesDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "movieDB"
                ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
            }
            return INSTANCE as AppDatabase
        }
    }
}