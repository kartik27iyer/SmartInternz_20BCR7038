package com.example.remote

import com.example.recipeapp.model.CookBook
import com.example.recipeapp.model.Recipe
import com.example.recipeapp.model.RecipeDetail
import com.example.recipeapp.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeApi {

    @GET("recipes/random")
    suspend fun getRandomRecipes(
        @Query("apiKey") apiKey : String = API_KEY,
        @Query("number") number : String
    ) : Recipe



    @GET("recipes/random")
    suspend fun getRecipeByTags(
        @Query("apiKey") apiKey: String = API_KEY,
        @Query("number") number: String = "10",
        @Query("tags") tags: String
    ) : Recipe

    @GET("recipes/{id}/information/")
    suspend fun getRecipeById(
        @Path("id") id : Int,
        @Query("apiKey") apiKey: String = API_KEY,
        @Query("includeNutrition") includeNutrition : Boolean = false
    ) : RecipeDetail
}