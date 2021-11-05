package com.juanvargas.konfioprueba.data

import com.juanvargas.konfioprueba.data.model.Dog

interface ListDogsRepository {
    suspend fun getListOfDogs():ArrayList<Dog>
}