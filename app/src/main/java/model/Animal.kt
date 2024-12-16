package model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "animals",
    foreignKeys = [
        ForeignKey(
            entity = Ubication::class,
            parentColumns = ["ubicationId"],
            childColumns = ["ubicationId"],
            onDelete = ForeignKey.CASCADE // Si se elimina la ubicación, elimina el animal.
        )
    ],
    indices = [Index(value = ["ubicacionId"])] // Crear índice para mejorar el rendimiento.
)
data class Animal(
    @PrimaryKey(autoGenerate = true)
    val animalId: Int = 0,
    val name: String,
    val specie: String,

    val ubicationId: Int
)