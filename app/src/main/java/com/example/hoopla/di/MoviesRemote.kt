package com.example.hoopla.di

import com.example.hoopla.data.datasource.remote.ApiInterceptor
import com.example.hoopla.data.datasource.remote.MoviesApi
import com.example.hoopla.data.datasource.remote.MoviesService
import com.example.hoopla.data.repository.MoviesRepositoryImpl
import com.example.hoopla.domain.repository.MoviesRepository
import com.example.hoopla.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MoviesRemote {

    private const val BASE_URL = Constants.BASE_URL

    @Provides
    fun injectInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }

    @Provides
    fun injectOkhttpClient(httpLoggingInterceptor: ApiInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    fun injectRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    fun injectMoviesService(retrofit: Retrofit): MoviesService = retrofit.create(MoviesService::class.java)

    @Provides
    @Singleton
    fun injectMoviesRepository(moviesApi: MoviesApi): MoviesRepository {
        return MoviesRepositoryImpl(moviesApi)
    }
}