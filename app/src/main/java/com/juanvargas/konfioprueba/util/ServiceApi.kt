package com.juanvargas.konfioprueba.util

import com.juanvargas.konfioprueba.data.model.Dog
import retrofit2.http.GET

interface ServiceApi {
    @GET("880188946124021760")
    suspend fun getListOfDogs():ArrayList<Dog>
}