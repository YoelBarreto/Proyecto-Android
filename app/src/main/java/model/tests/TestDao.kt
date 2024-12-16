package model.tests

import android.content.Context
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.CoroutineScope
import model.AppDatabase
import model.CompleteAnimal
import model.entities.Animal
import model.entities.Vacinne
import model.entities.Ubication


class TestDao(context: Context) {

    private val db: AppDatabase = AppDatabase.getDatabase(context)

    fun testInsert() {
        val completeAnimal = CompleteAnimal(
            name = "Alex",
            specie = "Perro",
            dateVacinne = "2024-12-16",
            x = 41.4036,
            y = 2.1744
        )

        CoroutineScope(Dispatchers.IO).launch {
            db.animalDao().insertCompleteAnimal(completeAnimal)
            Log.d("TestDao", "Animal, Vacuna y Ubicación insertados correctamente.")
        }
    }

    fun testUpdate() {
        val animalToUpdate = Animal(
            animalId = 1,
            name = "Alex actualizado",
            specie = "Perro"
        )

        CoroutineScope(Dispatchers.IO).launch {
            db.animalDao().updateAnimal(animalToUpdate)

            val vacinneToUpdate = Vacinne(
                vacinneId = 1,
                date = "2025-01-01",
                animalId = 1
            )
            db.animalDao().updateVacinne(vacinneToUpdate)

            val ubicationToUpdate = Ubication(
                ubicationId = 1,
                x = 40.7128,
                y = 74.0060,
                animalId = 1
            )
            db.animalDao().updateUbication(ubicationToUpdate)

            Log.d("TestDao", "Animal, Vacuna y Ubicación actualizados correctamente.")
        }
    }

    fun testDelete() {
        val animalIdToDelete = 1

        CoroutineScope(Dispatchers.IO).launch {
            db.animalDao().deleteAnimal(animalIdToDelete)
            Log.d("TestDao", "Animal con ID $animalIdToDelete y sus relaciones eliminados correctamente.")
        }
    }
}
