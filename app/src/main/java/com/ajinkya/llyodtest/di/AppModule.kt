package com.ajinkya.llyodtest.di

import android.app.Application
import android.content.Context
import com.ajinkya.llyodtest.BuildConfig
import com.ajinkya.llyodtest.api_calls.ApiService
import com.ajinkya.llyodtest.listeners.IProgressListener
import com.ajinkya.llyodtest.repository.ServerRepository
import com.ajinkya.llyodtest.repository.ServerRepositoryInterface
import com.ajinkya.llyodtest.util.CustomProgress
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL

    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }

    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(interceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofitInstance(baseUrl: String, httpClient: OkHttpClient): ApiService =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideServerRepo(apiService: ApiService) =
        ServerRepository(apiService) as ServerRepositoryInterface

    @Provides
    @Singleton
    fun provideProgressDialogLoading(): IProgressListener {
        return CustomProgress.instance!!
    }


}