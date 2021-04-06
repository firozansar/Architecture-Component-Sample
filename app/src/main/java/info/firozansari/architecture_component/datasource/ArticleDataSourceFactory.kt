package info.firozansari.architecture_component.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import kotlinx.coroutines.CoroutineScope

class ArticleDataSourceFactory(
    private val repository: ArticleRepository,
    private val scope: CoroutineScope
) : DataSource.Factory<Int, ArticleData>() {

    val source = MutableLiveData<ArticleDataSource>()

    override fun create(): DataSource<Int, ArticleData> {
        val source = ArticleDataSource(repository, scope)
        this.source.postValue(source)
        return source
    }

    fun getSource() = source.value
}