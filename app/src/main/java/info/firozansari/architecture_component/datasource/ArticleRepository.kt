package info.firozansari.architecture_component.datasource

import info.firozansari.architecture_component.api.ArticleService
import info.firozansari.architecture_component.datasource.ArticleData.Companion.mapList

/**
 * Created by Firoz Ansari on 15/10/2020.
 */
class ArticleRepository(private val articleService: ArticleService, private val dao: ArticleDao) {

    suspend fun getArticlesWithPagination(page: Int, limit: Int = 10): List<ArticleData> {
        val articles = articleService.getArticles(page, limit).await()
        return if (articles.isNotEmpty()) {
            val articleDataList = mapList(articleList = articles)
            saveAllArticleData(articleDataList)
            articleDataList
        } else {
            getAllArticleData()
        }
    }

    suspend fun saveArticleData(articleData: ArticleData) {
        dao.insertData(articleData)
    }

    private suspend fun saveAllArticleData(articleDataList: List<ArticleData>) {
        dao.insertList(articleDataList)
    }

    private fun getAllArticleData(): List<ArticleData> {
        return dao.getAllData()
    }

    suspend fun deleteArticleData(articleData: ArticleData) {
        dao.deleteData(articleData)
    }
}