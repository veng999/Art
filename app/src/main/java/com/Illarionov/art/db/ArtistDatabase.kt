package com.Illarionov.art.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.Illarionov.art.model.Work
import com.company.myartist.model.*
import com.company.myartist.model.Collection

@Database(
    entities = [
        Work::class,
        Media::class,
        Tag::class,
        Material::class,
        Genre::class,
        Style::class,
        Technique::class,
        Collection::class
    ], version = 1, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ArtistDatabase : RoomDatabase() {

    abstract fun workDao(): WorkDao

    abstract fun mediaDao(): MediaDao

    companion object {

        @Volatile
        private var instance: ArtistDatabase? = null
        private const val DATABASE_NAME = "ArtistDB"

        fun getInstance(context: Context): ArtistDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): ArtistDatabase {
            return Room.databaseBuilder(context, ArtistDatabase::class.java, DATABASE_NAME).build()
        }
    }
}