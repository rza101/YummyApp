package com.rhezarijaya.core.di

import com.rhezarijaya.core.BuildConfig
import com.rhezarijaya.core.data.source.remote.service.TheMealDbAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().setLevel(
                if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            )
        ).build()

    @Provides
    fun provideTheMealDBAPIService(okHttpClient: OkHttpClient): TheMealDbAPIService =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.THE_MEAL_DB_BASE_URL)
            .client(okHttpClient)
            .build()
            .create(TheMealDbAPIService::class.java)
}