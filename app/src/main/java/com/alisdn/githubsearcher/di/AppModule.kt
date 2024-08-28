package com.alisdn.githubsearcher.di

import android.content.Context
import androidx.databinding.library.BuildConfig
import com.alisdn.githubsearcher.data.network.ApiService
import com.alisdn.githubsearcher.utils.BASE_URL
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import java.io.File
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Reusable
    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.HEADERS)
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Reusable
    @Provides
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ) = OkHttpClient.Builder().apply {
        cache(
            Cache(
                directory = File(context.cacheDir, "http_cache"),
                maxSize = 5L * 1024 * 1024 // 5 MB
            )
        )
        if (BuildConfig.DEBUG) addInterceptor(httpLoggingInterceptor)
        connectTimeout(20, TimeUnit.SECONDS)
        readTimeout(20, TimeUnit.SECONDS)
        writeTimeout(20, TimeUnit.SECONDS)
        addInterceptor(ChuckerInterceptor(context))
    }.build()

    @Reusable
    @Provides
    fun provideKotlinSerialization(): Converter.Factory {
        val contentType = "application/json".toMediaType()
        val json = Json {
            encodeDefaults = true
            ignoreUnknownKeys = true
            isLenient = true
            explicitNulls = false
        }
        return json.asConverterFactory(contentType)
    }

    @Provides
    @Reusable
    fun provideRetrofit(
        client: OkHttpClient,
        kotlinSerializationFactory: Converter.Factory
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(kotlinSerializationFactory)
            .build()

    @Reusable
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

}