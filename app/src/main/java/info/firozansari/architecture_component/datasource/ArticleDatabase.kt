package info.firozansari.architecture_component.datasource

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Created by Firoz Ansari on 16/10/2020.
 */
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao

    companion object {
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