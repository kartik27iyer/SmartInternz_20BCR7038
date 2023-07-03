package com.example.recipeapp.recipelistscreen

import android.util.Log
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.recipeapp.viewmodels.HomeScreenViewModel

@Composable
fun RecipeListScreen(
    viewModel : HomeScreenViewModel = hiltViewModel(),
    navController: NavController,
    scaffoldState: ScaffoldState
) {

    val scrollState = rememberScrollState()

    val recipeList by remember {
        viewModel.recipeList
    }

    val isLoading by remember {
        viewModel.isLoading
    }
    Column(
        modifier = Modifier
            .padding(top = 50.dp)
            .scrollable(state = scrollState, orientation = Orientation.Vertical)
    ) {
        Text(
            text = "Daily Inspiration",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.padding(start = 20.dp)
        )

        if(isLoading){
            CircularProgressIndicator(
                color = MaterialTheme.colors.primary,
                modifier = Modifier.padding(start = 100.dp, top = 50.dp)
            )
        }
        LazyRow(
            contentPadding = PaddingValues(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(recipeList.recipes.size) {
                Recipe(recipe = recipeList.recipes[it], navController = navController, scaffoldState = scaffoldState)
            }
        }


    }

}
@Composable
fun VeganRecipeList(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    navController : NavController,
    scaffoldState: ScaffoldState
) {

    val veganRecipeList by remember {
        viewModel.veganRecipeList
    }
    val isLoading by remember {
        viewModel.isLoading
    }
    Spacer(modifier = Modifier.height(20.dp))

    Text(
        text = "Vegan Only",
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        modifier = Modifier.padding(start = 20.dp)
    )

    if (isLoading) {
        CircularProgressIndicator(
            color = MaterialTheme.colors.primary,
            modifier = Modifier.padding(start = 100.dp, top = 50.dp)
        )
    }
        LazyRow(
            contentPadding = PaddingValues(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(veganRecipeList.recipes.size) {

                Recipe(recipe = veganRecipeList.recipes[it], navController = navController, scaffoldState = scaffoldState)
            }
        }

}
@Composable
fun IndianRecipeList(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    navController: NavController,
    scaffoldState: ScaffoldState
) {

    val isLoading by remember {
        viewModel.isLoading
    }
    val indianRecipeList by remember {
        viewModel.indianRecipeList
    }
    Spacer(modifier = Modifier.height(20.dp))

    Text(
        text = "Indian Dishes",
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        modifier = Modifier.padding(start = 20.dp)
    )
    if(isLoading){
        CircularProgressIndicator(
            color = MaterialTheme.colors.primary,
            modifier = Modifier.padding(start = 100.dp, top = 50.dp)
        )
    }

    LazyRow(
        contentPadding = PaddingValues(20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(indianRecipeList.recipes.size) {
            Recipe(recipe = indianRecipeList.recipes[it],navController = navController, scaffoldState = scaffoldState)
        }
    }



}
