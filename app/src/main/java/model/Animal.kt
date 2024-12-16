package model

import androidx.room.Embedded
import androidx.room.Entity import androidx.room.PrimaryKey
import androidx.room.Relation

//@Entity(
//    tableName = "marks",
//    foreignKeys = [
//        androidx.room.ForeignKey(
//            entity = TypeMark::class,
//            parentColumns = ["id"],
//            childColumns = ["typeMarkId"],
//            onDelete = androidx.room.ForeignKey.CASCADE // Borrar tareas relacionadas al eliminar un tipo.
//        )
//    ],
//    indices = [androidx.room.Index(value = ["typeMarkId"])]
//)

data class Animal( @PrimaryKey(autoGenerate = true)
                 val id: Int = 0,
                 val name: String,
                 val owner: String,
)
