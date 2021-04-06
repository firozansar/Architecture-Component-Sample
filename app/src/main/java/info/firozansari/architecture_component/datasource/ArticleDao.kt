package info.firozansari.architecture_component.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

/**
 * Created by Firoz Ansari on 16/10/2020.
 */
@Dao
interface ArticleDao {

    @Query("SELECT * FROM Article ORDER BY id ASC")
    fun getAllData(): List<ArticleData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(articleData: ArticleData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(articles: List<ArticleData>)

    @Update
    suspend fun updateData(articleData: ArticleData)

    @Delete
    suspend fun deleteData(articleData: ArticleData)

    @Query("DELETE FROM Article")
    suspend fun deleteAll()
}