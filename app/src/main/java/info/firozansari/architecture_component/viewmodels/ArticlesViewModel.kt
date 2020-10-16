package info.firozansari.architecture_component.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import info.firozansari.architecture_component.api.NetworkState
import info.firozansari.architecture_component.datasource.ArticleDataSourceFactory
import info.firozansari.architecture_component.datasource.ArticleRepository
import info.firozansari.architecture_component.utils.pagedListConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

/**
 * Created by Firoz Ansari on 15/10/2020.
 */
class ArticlesViewModel (repo: ArticleRepository)  : ViewModel() {

    /**
     * Coroutines in a Main Thread
     */
    private val mainScope = CoroutineScope(Dispatchers.Main)

    /**
     * Coroutines in a Pool of Thread
     */
    private val ioScope = CoroutineScope(Dispatchers.Default)

    private val dataSource = ArticleDataSourceFactory(repository = repo, scope = ioScope)

    val articles = LivePagedListBuilder(dataSource, pagedListConfig()).build()
    val networkState: LiveData<NetworkState>? =
        Transformations.switchMap(dataSource.source) { it.getNetworkState() }

    fun refreshFailedRequest() =
        dataSource.getSource()?.retryFailedQuery()

    fun refreshAllList() =
        dataSource.getSource()?.refresh()

    override fun onCleared() {
        super.onCleared()
        mainScope.coroutineContext.cancel()
        ioScope.coroutineContext.cancel()
    }
}