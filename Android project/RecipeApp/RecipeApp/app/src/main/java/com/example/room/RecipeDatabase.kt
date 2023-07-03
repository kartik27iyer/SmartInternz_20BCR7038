package com.example.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.recipeapp.model.CookBook
import com.example.recipeapp.model.RecipeDetail
import com.example.recipeapp.model.RecipeX


@Database(entities = [RecipeDetail::class,CookBook::class], version = 3)
@TypeConverters(Converters::class)
abstract class RecipeDatabase :RoomDatabase() {

    abstract val dao : RecipeDao

    companion object{
        const val DATABASE_NAME = "recipe"
    }
}