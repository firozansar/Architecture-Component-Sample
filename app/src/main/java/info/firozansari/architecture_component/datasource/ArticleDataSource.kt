package info.firozansari.architecture_component.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import info.firozansari.architecture_component.api.ArticleService
import info.firozansari.architecture_component.api.NetworkState
import info.firozansari.architecture_component.models.Article
import kotlinx.coroutines.*

/**
 * Created by Firoz Ansari on 15/10/2020.
 */

class ArticleDataSource(
    private val repository: ArticleRepository,
    private val scope: CoroutineScope
) : PageKeyedDataSource<Int, ArticleData> (){

    private var supervisorJob = SupervisorJob()
    private val networkState = MutableLiveData<NetworkState>()
    private var retryQuery: (() -> Any)? = null //Keep the last query just in case it fails

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, ArticleData>) {
        retryQuery = { loadInitial(params, callback) }
        executeQuery(1) {
            callback.onResult(it, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ArticleData>) {
        val page = params.key
        retryQuery = { loadAfter(params, callback) }
        executeQuery(page) {
            callback.onResult(it, page + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ArticleData>) {
        //Not implemented
    }

    private fun executeQuery(
        page: Int,
        callback: (List<ArticleData>) -> Unit
    ) {
        networkState.postValue(NetworkState.RUNNING)
        scope.launch(getJobErrorHandler() + supervisorJob) {
            val artcleList = repository.getArticlesWithPagination(page)
            retryQuery = null
            networkState.postValue(NetworkState.SUCCESS)
            callback(artcleList)
        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, _ ->
        networkState.postValue(NetworkState.FAILED)
    }

    override fun invalidate() {
        super.invalidate()
        supervisorJob.cancelChildren()
    }

    fun getNetworkState(): LiveData<NetworkState> =
        networkState

    fun refresh() =
        this.invalidate()

    fun retryFailedQuery() {
        val prevQuery = retryQuery
        retryQuery = null
        prevQuery?.invoke()
    }

}