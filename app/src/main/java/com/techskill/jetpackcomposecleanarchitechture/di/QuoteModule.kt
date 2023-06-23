package com.techskill.jetpackcomposecleanarchitechture.di

import android.app.Application
import androidx.room.Room
import com.techskill.jetpackcomposecleanarchitechture.data.local.QuoteDatabase
import com.techskill.jetpackcomposecleanarchitechture.data.remote.QuotesApi
import com.techskill.jetpackcomposecleanarchitechture.domain.repository.QuoteRepository
import com.techskill.jetpackcomposecleanarchitechture.domain.repository.QuoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object QuoteModule {

    @Provides
    @Singleton
    fun provideQuoteApi(): QuotesApi {
        return Retrofit.Builder().baseUrl(QuotesApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuotesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideQuoteDatabase(app: Application): QuoteDatabase {
        return Room.databaseBuilder(
            app,
            QuoteDatabase::class.java,
            "quotes_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideQuoteRepository(db:QuoteDatabase,api:QuotesApi): QuoteRepository {
        return QuoteRepositoryImpl(api = api,db = db)
    }
}