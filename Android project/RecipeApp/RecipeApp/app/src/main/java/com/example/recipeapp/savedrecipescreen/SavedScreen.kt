package com.example.recipeapp.savedrecipescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.recipeapp.viewmodels.RecipeDetailScreenViewModel


@Composable
fun SavedScreen(
    viewModel: RecipeDetailScreenViewModel = hiltViewModel(),
    navController: NavController,
    scaffoldState: ScaffoldState
) {

    var selected by remember {
        mutableStateOf(viewModel.selected)
    }

    val cookBooks = viewModel.cookBooks.observeAsState(listOf()).value

    val recipeList = viewModel.getRecipeByCookBook(selected.value)
    val recipe = recipeList.observeAsState(listOf()).value
    Column (verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start, modifier = Modifier.padding(20.dp)){
        Text(
            text = "SAVED RECIPE",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            fontFamily = FontFamily.Serif
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Row {
            CookBook(name = "Favourites", navController = navController)

            LazyRow(){

                items(cookBooks.size){

                    CookBook(name = cookBooks.get(it).name, navController = navController)
                }

            }

            CookBook(name = "New cookbook", navController = navController)

        }
        
        Spacer(modifier = Modifier.height(20.dp))

        if(recipe.isEmpty()){
            Text(
                textAlign = TextAlign.Center,
                text = "Explore the app and save your favourite recipe here",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .align(CenterHorizontally)
            )
        }
        LazyVerticalGrid(modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 10.dp),
            columns = GridCells.Adaptive(minSize = 120.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        )
        {
           items(recipe){
               SavedRecipeItem(modifier = Modifier.fillMaxWidth(), recipe = it, navController = navController, scaffoldState = scaffoldState)
           }
        }
    }


}


@Composable
fun CookBookSection() {



}

@Composable
fun CookBook(
    name : String,
    viewModel: RecipeDetailScreenViewModel = hiltViewModel(),
    navController: NavController
) {

    val interactionSource = MutableInteractionSource()


    var currentName by remember {
        mutableStateOf(name)
    }
    var nameSelected by remember {
        mutableStateOf(viewModel.selected)
    }
    var isSelected by remember {
        mutableStateOf(currentName == nameSelected.value)
    }
    var borderWidth =  if(isSelected) 2.dp else 0.dp
    Column(

        modifier = Modifier
            .padding(start = 20.dp)
            .clickable(
                interactionSource = interactionSource,
                null
            ) {
                viewModel.selected.value = name
                nameSelected.value = name
                currentName = name
                isSelected = true
            },


    ){
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .align(CenterHorizontally),
            contentAlignment = Alignment.Center
        ){
            if(name.equals("Favourites")){
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favourite",
                modifier = Modifier
                    .border(
                        width = 1.dp, color = if (viewModel.selected.value == currentName) {
                            Color.Black
                        } else {
                            Color.LightGray
                        }, shape = CircleShape
                    )
                    .align(Alignment.Center)
                    .height(70.dp)
                    .width(70.dp),
                tint = Color.Red
            )}
            else if(name.equals("New cookbook")){
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Favourite",
                    modifier = Modifier
                        .border(
                            width = 3.dp, color = if (viewModel.selected.value == currentName) {
                                Color.Black
                            } else {
                                Color.LightGray
                            }, shape = CircleShape
                        )
                        .clickable {
                            navController.navigate("insert_cookbook_screen")
                        }
                        .height(70.dp)
                        .width(70.dp),
                    tint = Color.Black
                )
            }
            else{
                Image(
                    painter = painterResource(id = com.example.recipeapp.R.drawable.cookbook),
                    contentDescription = "",
                    modifier = Modifier
                        .border(
                            width = 3.dp, color = if (viewModel.selected.value == currentName) {
                                Color.Black
                            } else {
                                Color.LightGray
                            }, shape = CircleShape
                        )
                        .align(Alignment.Center)
                        .height(70.dp)
                        .width(70.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            modifier = Modifier.padding(start = 7.dp),
            text = name,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 12.sp
        )

    }

}