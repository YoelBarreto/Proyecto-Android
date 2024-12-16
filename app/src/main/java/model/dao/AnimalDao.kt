package model.dao

import androidx.room.*
import model.entities.Animal
import model.entities.Vacinne
import model.entities.Ubication
import model.relations.UbicationWithAnimal
import model.relations.VacinneWithAnimal
import model.CompleteAnimal

@Dao
interface AnimalDao {

    // Insertar un Animal
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnimal(animal: Animal): Long

    // Insertar una Vacuna
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVacinne(vacinne: Vacinne)

    // Insertar una Ubicación
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUbication(ubication: Ubication)

    // Insertar Animal Completo (con Vacuna y Ubicación)
    @Transaction
    suspend fun insertCompleteAnimal(completeAnimal: CompleteAnimal) {
        // Insertar el Animal y obtener su ID
        val animalId = insertAnimal(
            Animal(
                name = completeAnimal.name,
                specie = completeAnimal.specie
            )
        ).toInt()

        // Insertar la Vacuna
        insertVacinne(
            Vacinne(
                date = completeAnimal.dateVacinne,
                animalId = animalId
            )
        )

        // Insertar la Ubicación
        insertUbication(
            Ubication(
                x = completeAnimal.x,
                y = completeAnimal.y,
                animalId = animalId
            )
        )
    }

    // Consulta para obtener las ubicaciones con nombre del animal
    @Query("""
        SELECT a.name AS nameAnimal, u.x, u.y
        FROM animals a
        INNER JOIN ubications u ON a.animalId = u.animalId
    """)
    suspend fun getUbicationsWithAnimal(): List<UbicationWithAnimal>

    // Consulta para obtener las vacunas con nombre del animal
    @Query("""
        SELECT a.name AS nameAnimal, v.date AS dateVacinne
        FROM animals a
        INNER JOIN vacinnes v ON a.animalId = v.animalId
    """)
    suspend fun getVacinnesWithAnimal(): List<VacinneWithAnimal>

    // Metodo para eliminar un Animal junto con sus Vacunas y Ubicación
    @Transaction
    @Query("DELETE FROM animals WHERE animalId = :animalId")
    suspend fun deleteAnimal(animalId: Int)

    // Metodo para eliminar la Vacuna
    @Query("DELETE FROM vacinnes WHERE animalId = :animalId")
    suspend fun deleteVacinne(animalId: Int)

    // Metodo para eliminar la Ubicación
    @Query("DELETE FROM ubications WHERE animalId = :animalId")
    suspend fun deleteUbication(animalId: Int)
}
