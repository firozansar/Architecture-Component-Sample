package info.firozansari.architecture_component.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import info.firozansari.architecture_component.models.Article
import kotlinx.coroutines.CoroutineScope

/**
 * Created by Firoz Ansari on 15/10/2020.
 */
class ArticleDataSourceFactory (
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