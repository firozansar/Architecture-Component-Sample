package info.firozansari.architecture_component.datasource

import androidx.room.Entity
import androidx.room.PrimaryKey
import info.firozansari.architecture_component.models.Article

/**
 * Created by Firoz Ansari on 16/10/2020.
 */
@Entity(tableName = "Article")
data class ArticleData (
    @PrimaryKey
    var id: Int,
    var username: String,
    var designation: String,
    var userimage: String,
    var blogimage: String?,
    var blogtitle: String?,
    var blogurl: String?,
    var blogcontent: String,
    var created: String,
    var likes: Int,
    var comments: Int
){
    companion object {
        fun map(article: Article): ArticleData {
            return ArticleData(
                id = article.id,
                username = article.user[0].name,
                designation = article.user[0].designation,
                userimage = article.user[0].avatar,
                blogimage =  getBlogImage(article),
                blogtitle = getBlogTitle(article),
                blogurl = getBlogUrl(article),
                blogcontent = article.content,
                created = article.createdAt,
                likes = article.likes,
                comments = article.comments
            )
        }

        private fun getBlogImage(article: Article): String{
            if(article.media.isNotEmpty()){
                 return article.media[0].image
            } else {
                return ""
            }
        }

        private fun getBlogTitle(article: Article): String{
            if(article.media.isNotEmpty()){
                return article.media[0].title
            } else {
                return ""
            }
        }

        private fun getBlogUrl(article: Article): String{
            if(article.media.isNotEmpty()){
                return article.media[0].url
            } else {
                return ""
            }
        }

        fun mapList(articleList: List<Article>): List<ArticleData> {
            val articleDataList = mutableListOf<ArticleData>()
            for (recipe in articleList) {
                articleDataList.add(map(recipe))
            }
            return articleDataList
        }
    }
}