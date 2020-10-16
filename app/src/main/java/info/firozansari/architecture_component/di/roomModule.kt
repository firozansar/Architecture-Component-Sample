package info.firozansari.architecture_component.di

import info.firozansari.architecture_component.datasource.ArticleDatabase
import org.koin.dsl.module

/**
 * Created by Firoz Ansari on 16/10/2020.
 */
val roomModule = module {
    single { ArticleDatabase.getDatabase(get()) }
    single { get<ArticleDatabase>().getArticleDao() }
}