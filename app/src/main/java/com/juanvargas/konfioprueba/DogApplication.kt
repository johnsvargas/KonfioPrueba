package com.juanvargas.konfioprueba

import android.app.Application
import com.juanvargas.konfioprueba.data.DogRoomDatabase

class DogApplication: Application()  {

    val database by lazy { DogRoomDatabase.getDatabase(this) }
}