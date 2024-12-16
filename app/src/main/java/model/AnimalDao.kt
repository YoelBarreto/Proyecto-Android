package model

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MarkDao {
    @Insert
    suspend fun insertMark(animal: Animal)

    @Query("SELECT * FROM animals")
    fun getAllMarksAndTypes(): Flow<List<MarkWithTypeMark>>
}
