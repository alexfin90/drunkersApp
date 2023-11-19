package com.softdream.drunkersapp.data.di

import android.content.Context
import androidx.room.Room
import com.softdream.drunkersapp.BuildConfig
import com.softdream.drunkersapp.data.local.CocktailDao
import com.softdream.drunkersapp.data.local.CocktailsDb
import com.softdream.drunkersapp.data.remote.CocktailApiService

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CocktailsModule {

    @Provides
    fun provideRoomDao(database: CocktailsDb): CocktailDao {
        return database.dao
    }

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext appContext: Context): CocktailsDb {
        return Room.databaseBuilder(
            appContext,
            CocktailsDb::class.java,
            "cocktails_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().addConverterFactory(
            GsonConverterFactory.create()
        ).baseUrl(BuildConfig.COCKTAIL_BASE_URL).build()
    }

    @Provides
    fun provideRetrofitApi(retrofit: Retrofit):
            CocktailApiService {
        return retrofit.create(CocktailApiService::class.java)
    }
}