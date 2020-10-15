package info.firozansari.architecture_component

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import info.firozansari.architecture_component.api.ArticleService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Firoz Ansari on 15/10/2020.
 */
fun networkMockedComponent(mockApi: String) = module {

    single { GsonBuilder().create() }

    single {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        OkHttpClient.Builder().apply {
            retryOnConnectionFailure(true)
            addInterceptor(httpLoggingInterceptor)
        }.build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(mockApi)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(get())
            .build()
    }

    factory { get<Retrofit>().create(ArticleService::class.java) }
}