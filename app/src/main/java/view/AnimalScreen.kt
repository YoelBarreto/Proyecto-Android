package view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import model.Animal
import model.AppDatabase

@Composable
fun AnimalScreen(database: AppDatabase, onNavigateBack: () -> Unit) {
    val typeTaskDao = database.typeTaskDao()
    val scope = rememberCoroutineScope()
    var typeTasks by remember { mutableStateOf(listOf<Animal>()) }

    // Tipo tareas
    var newTypeTaskName by remember { mutableStateOf("") }

    // Cargar tareas al iniciar
    LaunchedEffect(Unit) {
        try {
            typeTasks = typeTaskDao.getAllTypeTasks()
        } catch (e: Exception) {
            Log.i("Error", e.toString())
        }
    }
    Button(onClick = { onNavigateBack() }, modifier = Modifier.padding(10.dp)) {
        Text("Volver")
    }
    Column(
        modifier = Modifier
            .background(Color(0xFFEAA9FF))
            .fillMaxSize()
            .padding(15.dp)
            .verticalScroll(state = rememberScrollState()),
    ) {
        Button(onClick = { onNavigateBack() }, modifier = Modifier.padding(10.dp)) {
            Text("Volver")
        }
        Row {
            OutlinedTextField(
                value = newTypeTaskName,
                onValueChange = { newTypeTaskName = it },
                label = {
                    Text(
                        text = "Tipo tipo",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold
                        ),)

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 3.dp)
//                    .background(Color(0xFFF1C7FF))
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = Modifier
                    .width(170.dp)
                    .height(50.dp),
                onClick = {
                    // Corrutina para añadir tarea
                    scope.launch(Dispatchers.IO) {
                        try {
                            typeTaskDao.insertTypeTask(
                                TypeTask(
                                    titulo = newTypeTaskName,
                                )
                            )
                            // Limpiar los cambios de input
                            newTypeTaskName = ""

                            // Refresca las lista de tareas
                            typeTasks = typeTaskDao.getAllTypeTasks()
                        } catch (e: Exception) {
                            Log.i("Error", e.toString())
                        }
                    }
                }
            ) {
                Text("Añadir Tarea")
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Lista de Tareas",
                    modifier = Modifier.padding(bottom = 35.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 35.sp
                )
                Column {
                    // Mostrar la lista de tipo de tareas
                    typeTasks.forEach { typeTask ->
                        // Tarjeta de tipos
                        Box(
                            modifier = Modifier
                                .padding(bottom = 10.dp)
                                .clip(shape = RoundedCornerShape(10.dp))
                                .background(Color(0xFFF1C7FF))
                                .height(75.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxSize(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(
                                    modifier = Modifier
                                        .weight(0.35f)
                                        .padding(start = 5.dp, top = 10.dp)
                                ) {
                                    Text(
                                        text = typeTask.titulo,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                Row(
                                    modifier = Modifier
                                        .weight(0.4f)
                                        .padding(end = 5.dp),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    androidx.compose.material.IconButton(
                                        modifier = Modifier,
                                        onClick = {
                                        }
                                    ) {
                                        Icon(Icons.Default.Edit, contentDescription = "Editar")
                                    }
                                    androidx.compose.material.IconButton(
                                        modifier = Modifier,
                                        onClick = {
                                            scope.launch(Dispatchers.IO) {
                                                try {
                                                    typeTaskDao.deleteTypeTask(typeTask)

                                                    typeTasks = typeTaskDao.getAllTypeTasks()
                                                } catch (e: Exception) {
                                                    Log.i("Error", e.toString())

                                                }
                                            }
                                        }
                                    ) {
                                        Icon(Icons.Default.Delete, contentDescription = "Eliminar")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}