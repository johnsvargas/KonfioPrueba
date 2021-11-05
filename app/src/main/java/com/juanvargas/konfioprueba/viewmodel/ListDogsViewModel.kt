package com.juanvargas.konfioprueba.viewmodel

import androidx.lifecycle.*
import com.juanvargas.konfioprueba.data.ListDogsRepositoryImpl
import com.juanvargas.konfioprueba.data.dao.DogDao
import com.juanvargas.konfioprueba.data.model.Dog
import com.juanvargas.konfioprueba.util.LiveDataState
import com.juanvargas.konfioprueba.util.MutableLiveDataState
import com.juanvargas.konfioprueba.util.StateApp
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListDogsViewModel(private val dogDao: DogDao):ViewModel() {

    private val repository by lazy {ListDogsRepositoryImpl(dogDao)}

    private val _listOfDogs: MutableLiveDataState<ArrayList<Dog>> by lazy { MutableLiveDataState() }
    val listOfDogs: LiveDataState<ArrayList<Dog>> = _listOfDogs

    private val _isLanding : MutableLiveData<Boolean> by lazy{ MutableLiveData() }
    val isLanding: LiveData<Boolean> = _isLanding.distinctUntilChanged()

    fun getListOfDogs(){
        _isLanding.value = true
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler{ _, throwable ->
            _listOfDogs.postValue(StateApp.Error(throwable))
            _isLanding.postValue(false)
        }){
            _isLanding.postValue(false)
            val response = repository.getListOfDogs()
            _listOfDogs.postValue(StateApp.Success(response))
        }
    }

    fun clearListOfDogs(){
        _listOfDogs.value = StateApp.Success(ArrayList())
    }
}

class ListDogsModelFactory(private val dogDao: DogDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListDogsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ListDogsViewModel(dogDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}