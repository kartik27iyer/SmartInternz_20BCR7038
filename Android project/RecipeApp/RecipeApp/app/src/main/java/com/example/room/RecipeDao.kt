package com.example.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recipeapp.model.CookBook
import com.example.recipeapp.model.Recipe
import com.example.recipeapp.model.RecipeDetail
import com.example.recipeapp.model.RecipeX
import retrofit2.http.GET


@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveRecipe(recipe : RecipeDetail)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCookbook(cookBook: CookBook)

    @Query("SELECT * FROM cookbook")
    fun getCookbooks() : LiveData<List<CookBook>>


    @Query("SELECT * from recipedetail WHERE cookBook = :cookBook")
     fun getRecipeByCookbook(cookBook: String) : LiveData<List<RecipeDetail>>

}