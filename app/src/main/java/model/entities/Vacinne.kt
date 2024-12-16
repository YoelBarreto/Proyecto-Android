package model.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "vacinnes",
    foreignKeys = [
        ForeignKey(
            entity = Animal::class,
            parentColumns = ["animalId"],
            childColumns = ["animalId"],
            onDelete = ForeignKey.CASCADE // Elimina las vacunas relacionadas cuando se borra el animal
        )
    ]
)
data class Vacinne(
    @PrimaryKey(autoGenerate = true)
    val vacinneId: Int = 0,
    val date: String,
    val animalId: Int
)