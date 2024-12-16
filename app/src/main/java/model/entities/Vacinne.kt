package model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "vacinnes",
)
data class Vacinne(
    @PrimaryKey(autoGenerate = true)
    val vacinneId: Int = 0,
    val date: String,
    val animalId: Int
)