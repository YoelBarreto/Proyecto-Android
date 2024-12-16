package model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "vacinnes",
)
data class Vacinne(
    @PrimaryKey(autoGenerate = true)
    val vacinneId: Int = 0,
    val date: String
)