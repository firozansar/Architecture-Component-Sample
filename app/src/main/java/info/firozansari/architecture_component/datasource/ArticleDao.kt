package info.firozansari.architecture_component.datasource

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Created by Firoz Ansari on 16/10/2020.
 */
@Dao
interface ArticleDao {

    @Query("SELECT * FROM Article ORDER BY id ASC")
    fun getAllData(): LiveData<List<ArticleData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(articleData: ArticleData)

    @Update
    suspend fun updateData(articleData: ArticleData)

    @Delete
    suspend fun deleteItem(articleData: ArticleData)

    @Query("DELETE FROM Article")
    suspend fun deleteAll()

}