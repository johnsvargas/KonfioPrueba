package com.juanvargas.konfioprueba.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.juanvargas.konfioprueba.data.model.Dog

@Entity(tableName = "dog_table")
data class DogEntity (
    @PrimaryKey(autoGenerate = true)
    val uid: Int,
    @ColumnInfo(name = "dog_name")
    var dogName:String,
    @ColumnInfo(name = "description")
    var description:String,
    @ColumnInfo(name = "age")
    var age:Int,
    @ColumnInfo(name = "image")
    var image:String
    )

fun DogEntity.toDog():Dog{
    return Dog(this.dogName,this.description,this.age,this.image)
}
