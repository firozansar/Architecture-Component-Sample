package info.firozansari.architecture_component.api

import info.firozansari.architecture_component.models.Article
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Firoz Ansari on 15/10/2020.
 */

interface ArticleService {
    @GET("blogs")
    fun getArticles(
        @Query("page") page: Int,
        @Query("limit") min: Int
    ): Deferred<List<Article>>
}