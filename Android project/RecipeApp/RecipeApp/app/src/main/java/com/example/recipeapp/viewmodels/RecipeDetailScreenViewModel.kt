package com.example.recipeapp.viewmodels

import android.util.Log
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.colorResource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipeapp.model.CookBook
import com.example.recipeapp.model.RecipeDetail
import com.example.recipeapp.utils.Resource
import com.example.remote.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RecipeDetailScreenViewModel @Inject constructor(
    private val repository: RecipeRepository
): ViewModel(){

    val cookBooks : LiveData<List<CookBook>>
    var recipe : LiveData<List<RecipeDetail>>


    var selected = mutableStateOf("Favourites")
    suspend fun loadRecipeDetail(id:Int) : Resource<RecipeDetail>{
          return  repository.getRecipeDetailById(id = id)
    }

    suspend fun saveRecipe(recipeDetail: RecipeDetail) {
        repository.saveRecipe(recipeDetail)
    }


    fun getRecipeByCookBook(cookBook: String):LiveData<List<RecipeDetail>>{
              return  repository.getRecipeByCookbook(cookBook = cookBook)
        }

    init {
        cookBooks = repository.getCookBooks()
        recipe = repository.getRecipeByCookbook("z")

    }

    suspend fun insertCookbook(cookBook: CookBook){
        repository.insertCookbook(cookBook)
    }


}