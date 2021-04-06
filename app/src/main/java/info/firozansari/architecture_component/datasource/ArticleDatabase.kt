package info.firozansari.architecture_component.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import info.firozansari.architecture_component.datasource.ArticleDatabase.Companion.DB_VERSION

/**
 * Created by Firoz Ansari on 16/10/2020.
 */
@Database(entities = [ArticleData::class], version = DB_VERSION, exportSchema = false)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun getArticleDao(): ArticleDao

    companion object {
        const val DB_VERSION = 1

        @Volatile
        private var INSTANCE: ArticleDatabase? = null

        fun getDatabase(context: Context): ArticleDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java, "article_database"
            )
                .build()
    }
}