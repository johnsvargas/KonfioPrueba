package com.juanvargas.konfioprueba.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.juanvargas.konfioprueba.data.dao.DogDao
import com.juanvargas.konfioprueba.data.entity.DogEntity


@Database(entities = [DogEntity::class], version = 1)
abstract class DogRoomDatabase : RoomDatabase() {
    abstract fun dogDao(): DogDao

    companion object {
        @Volatile
        private var INSTANCE: DogRoomDatabase? = null

        fun getDatabase(
            context: Context
        ): DogRoomDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DogRoomDatabase::class.java,
                    "word_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return
                instance
            }
        }

    }

}