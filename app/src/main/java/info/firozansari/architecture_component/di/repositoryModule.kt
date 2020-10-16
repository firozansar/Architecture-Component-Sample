package info.firozansari.architecture_component.di

import info.firozansari.architecture_component.datasource.ArticleRepository
import org.koin.dsl.module

/**
 * Created by Firoz Ansari on 15/10/2020.
 */

val repositoryModule = module {
    factory { ArticleRepository(get(), get()) }
}