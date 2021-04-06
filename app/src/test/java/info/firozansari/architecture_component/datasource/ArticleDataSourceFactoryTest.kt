package info.firozansari.architecture_component.datasource

import info.firozansari.architecture_component.BaseMockServerTest
import info.firozansari.architecture_component.di.repositoryModule
import info.firozansari.architecture_component.di.viewModelModule
import info.firozansari.architecture_component.networkMockedComponent
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.test.inject
import retrofit2.HttpException
import java.net.HttpURLConnection

@RunWith(JUnit4::class)
class ArticleDataSourceFactoryTest : BaseMockServerTest() {
    private val articleRepository by inject<ArticleRepository>()

    override fun setUp() {
        super.setUp()
        startKoin {
            modules(
                listOf(
                    viewModelModule,
                    repositoryModule,
                    networkMockedComponent(mockServer.url("/").toString())
                )
            )
        }
    }

    @Test
    fun get_articles_result_ok() {
        mockHttpResponse("result_articles_mocked.json", HttpURLConnection.HTTP_OK)
        runBlocking {
            val articleListMocked = articleRepository.getArticlesWithPagination(1)
            assertNotNull(articleListMocked)
            assertEquals(articleListMocked.isNullOrEmpty(), false)
        }
    }

    @Test
    fun get_articles_result_ok_single_recipe() {
        mockHttpResponse("result_single_article_mocked.json", HttpURLConnection.HTTP_OK)
        runBlocking {
            val articleListMocked = articleRepository.getArticlesWithPagination(1)
            assertNotNull(articleListMocked)
            assertEquals(articleListMocked.isNullOrEmpty(), false)
            val article = articleListMocked[0]
            assertEquals(article.id, 1)
        }
    }

    @Test(expected = HttpException::class)
    fun search_recipes_result_error() {
        mockHttpResponse("result_articles_mocked.json", HttpURLConnection.HTTP_BAD_REQUEST)
        runBlocking {
            val recipesListMocked = articleRepository.getArticlesWithPagination(1)
            assertEquals(recipesListMocked.isNullOrEmpty(), true)
        }
    }


}