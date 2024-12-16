package model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "vacinnes",
    foreignKeys = [
        ForeignKey(
            entity = Animal::class,
            parentColumns = ["animalId"],
            childColumns = ["animalId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["animalId"])]
)
data class Vacinne(
    @PrimaryKey(autoGenerate = true)
    val vacinneId: Int = 0,
    val name: String,
    val date: String,
    val animalId: Int
)