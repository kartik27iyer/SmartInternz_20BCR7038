package com.example.recipeapp.di

import android.app.Application
import androidx.room.Room
import androidx.room.util.DBUtil
import com.example.recipeapp.model.RecipeX
import com.example.recipeapp.utils.Constants.BASE_URL
import com.example.remote.RecipeApi
import com.example.remote.RecipeRepository
import com.example.room.RecipeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideRecipeDatabase(app:Application): RecipeDatabase {
        return Room.databaseBuilder(
            app,
            RecipeDatabase::class.java,
            RecipeDatabase.DATABASE_NAME,
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideRecipeRepository(api:RecipeApi,db: RecipeDatabase) = RecipeRepository(api,db)


    @Provides
    @Singleton
    fun provideRecipeApi(): RecipeApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecipeApi::class.java)
    }


}