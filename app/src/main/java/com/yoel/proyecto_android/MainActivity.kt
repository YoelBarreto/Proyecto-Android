package com.yoel.proyecto_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
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
//                AnimalScreen(database)
                // Al no tener interfaz, por lo menos tener algo en pantalla
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF2d88c8)),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "N/A", fontSize = 46.sp, fontWeight = FontWeight.Bold)
                }
            }

//            // Sin interfáz
//            // Insertar los datos mediante CompleteAnimal, insertando todos los datos.
//            testDao.testInsert()
//
//            // Actualizar/Cambiar los datos mediante CompleteAnimal.
//            testDao.testUpdate()
//
//            // Eliminar los datos mediante ID
//            testDao.testDelete()
        }
    }
}