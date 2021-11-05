package com.juanvargas.konfioprueba.data.model

import com.juanvargas.konfioprueba.data.entity.DogEntity

data class Dog(
    var dogName:String,
    var description:String,
    var age:Int,
    var image:String
)

fun Dog.toDogEntity():DogEntity{
    return DogEntity(0,this.dogName,this.description,this.age,this.image)
}
