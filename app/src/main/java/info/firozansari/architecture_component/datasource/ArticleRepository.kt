package info.firozansari.architecture_component.datasource

import info.firozansari.architecture_component.api.ArticleService
import info.firozansari.architecture_component.models.Article

/**
 * Created by Firoz Ansari on 15/10/2020.
 */
class ArticleRepository(private val articleService: ArticleService) {

    suspend fun getArticlesWithPagination(page: Int, limit: Int = 10): List<Article> {
         return articleService.getArticles(page, limit).await()
    }
}