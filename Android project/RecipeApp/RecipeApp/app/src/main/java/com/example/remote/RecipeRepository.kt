package com.example.remote

import android.util.Log
import androidx.compose.ui.platform.LocalDensity
import androidx.lifecycle.LiveData
import com.example.recipeapp.model.CookBook
import com.example.recipeapp.model.Recipe
import com.example.recipeapp.model.RecipeDetail
import com.example.recipeapp.utils.Constants.API_KEY
import com.example.recipeapp.utils.Resource
import com.example.room.RecipeDatabase
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@ActivityScoped
class RecipeRepository @Inject constructor(
    private val api : RecipeApi,
    private val db : RecipeDatabase
){
     fun getCookBooks() : LiveData<List<CookBook>> {
        val list =  db.dao.getCookbooks()
        return list
    }
    suspend fun saveRecipe(recipeDetail: RecipeDetail) {
         db.dao.saveRecipe(recipeDetail)
    }
    suspend fun getRecipeDetailById(id:Int) : Resource<RecipeDetail>
    {
        val response = try {
            api.getRecipeById(id = id)
        }catch (e : java.lang.Exception){
            Log.d("api_response",e.message!!)
            return  Resource.Error(null,e.message!!)
        }
        return  Resource.Success(response)
    }
   suspend fun getRandomRecipe(apiKey : String=API_KEY,number:String) : Resource<Recipe>
   {
       val response = try {
           api.getRandomRecipes(apiKey,number)
       }catch (e : java.lang.Exception){

         Log.d("api_response",e.message!!)
         return  Resource.Error(null,e.message!!)
       }
      return  Resource.Success(response)
   }

    suspend fun getRecipeByTags(apiKey: String = API_KEY,number: String,tag:String) : Resource<Recipe>
    {
        val response = try {
            api.getRecipeByTags(apiKey,number,tag)
        }catch (e : java.lang.Exception){
            Log.d("api_response",e.message!!)
            return  Resource.Error(null,e.message!!)
        }
        return  Resource.Success(response)
    }

    suspend fun insertCookbook(cookBook: CookBook) {
        db.dao.saveCookbook(cookBook)
    }

     fun getRecipeByCookbook(cookBook : String) : LiveData<List<RecipeDetail>>{
       return  db.dao.getRecipeByCookbook(cookBook)
    }
}