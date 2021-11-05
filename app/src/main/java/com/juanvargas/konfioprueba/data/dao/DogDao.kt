package com.juanvargas.konfioprueba.data.dao

import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.juanvargas.konfioprueba.data.entity.DogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DogDao {

    @Query("SELECT * FROM dog_table")
    fun getListOfDogs(): List<DogEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(dog: DogEntity)

    @Query("DELETE FROM dog_table")
    suspend fun deleteAll()
}