package info.firozansari.architecture_component.di

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import info.firozansari.architecture_component.api.ArticleService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val CONNECT_TIMEOUT = 10L
private const val BASE_URL = "https://5e99a9b1bc561b0016af3540.mockapi.io/jet2/api/v1/"

val networkModule = module {

    single { GsonBuilder().create() }

    single {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        OkHttpClient.Builder().apply {
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            retryOnConnectionFailure(true)
            addInterceptor(httpLoggingInterceptor)
        }.build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(get())
            .build()
    }

    factory { get<Retrofit>().create(ArticleService::class.java) }
}