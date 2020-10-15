package info.firozansari.architecture_component.di

import info.firozansari.architecture_component.viewmodels.ArticlesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Firoz Ansari on 15/10/2020.
 */
val viewModelModule = module {
    viewModel { ArticlesViewModel(get()) }
}