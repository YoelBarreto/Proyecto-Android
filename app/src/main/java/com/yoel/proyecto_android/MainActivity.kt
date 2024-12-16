package com.yoel.proyecto_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import model.AppDatabase
import view.AnimalScreen
import model.tests.TestDao


class MainActivity : ComponentActivity() {
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        database = AppDatabase.getDatabase(this)
        val testDao = TestDao(applicationContext)
        setContent {
            Surface(modifier = Modifier.fillMaxSize()) {
                AnimalScreen(database)
            }

//            // Sin interfáz
//            // Insertar datos
//            testDao.testInsert()
//
//            // Actualizar datos
//            testDao.testUpdate()
//
//            // Eliminar datos
//            testDao.testDelete()
        }
    }
}