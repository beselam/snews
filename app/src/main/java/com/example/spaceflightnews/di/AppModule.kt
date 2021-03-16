package com.example.spaceflightnews.di

import com.example.spaceflightnews.network.NewsApi
import com.example.spaceflightnews.repository.DefaultNewsAppRespository
import com.example.spaceflightnews.repository.NewsAppRespository
import com.example.spaceflightnews.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideNewsApi(): NewsApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsApi::class.java)


    @Singleton
    @Provides
    fun provideDefaultNewsAppRepository(newsApi:  NewsApi): NewsAppRespository = DefaultNewsAppRespository(newsApi)

}