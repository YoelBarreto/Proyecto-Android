package model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import model.dao.AnimalDao
import model.entities.Animal
import model.entities.Ubication
import model.entities.Vacinne

@Database(entities = [Animal::class, Vacinne::class, Ubication::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun animalDao(): AnimalDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "care-animal_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}