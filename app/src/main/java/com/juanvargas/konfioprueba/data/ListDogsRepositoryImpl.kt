package com.juanvargas.konfioprueba.data

import com.juanvargas.konfioprueba.data.dao.DogDao
import com.juanvargas.konfioprueba.data.entity.DogEntity
import com.juanvargas.konfioprueba.data.entity.toDog
import com.juanvargas.konfioprueba.data.model.Dog
import com.juanvargas.konfioprueba.data.model.toDogEntity
import com.juanvargas.konfioprueba.util.NetworkClient
import com.juanvargas.konfioprueba.util.ServiceApi
import retrofit2.create

class ListDogsRepositoryImpl(private val dogDao: DogDao):ListDogsRepository {
    val services =  NetworkClient.buildRetrofitClient().create<ServiceApi>()


    override suspend fun getListOfDogs(): ArrayList<Dog> {
        val list = dogDao.getListOfDogs()

        if(list.isEmpty()){
            insertInDB(services.getListOfDogs())
        }
        return convertArrayDog(dogDao.getListOfDogs())
    }

    private suspend fun insertInDB(list: ArrayList<Dog>){
        list.forEach {
            dogDao.insert(it.toDogEntity())
        }

    }

    private fun  convertArrayDog(list:List<DogEntity>):ArrayList<Dog>{
        val newList = ArrayList<Dog>()
        list.forEach {
            newList.add(it.toDog())
        }
        return newList
    }
}