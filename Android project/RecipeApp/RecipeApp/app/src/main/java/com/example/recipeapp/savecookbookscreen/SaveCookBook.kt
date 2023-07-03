package com.example.recipeapp.savecookbookscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BackdropScaffoldState
import androidx.compose.material.Icon
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.viewmodels.RecipeDetailScreenViewModel
import kotlinx.coroutines.launch


@Composable
fun SaveCookBook(
    viewModel : RecipeDetailScreenViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState,
    navController: NavController
)
{
    var text by remember {
        mutableStateOf("")
    }


    val scope = rememberCoroutineScope()


    Column(modifier = Modifier.fillMaxSize())
    {
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween)
        {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back" )
            Text(
                text = "CREATE",
                fontSize = 15.sp,
                color = if(text.isNotEmpty()) Color.Black else Color.LightGray,
                modifier = Modifier
                    .clickable {
                        if(text.isNotEmpty()){
                            scope.launch {
                                viewModel.insertCookbook(com.example.recipeapp.model.CookBook(name = text))
                                scaffoldState.snackbarHostState.showSnackbar("Recipe Saved Successfully")
                                navController.popBackStack()
                            }
                        }
                    }
            )

        }

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier.padding(start= 20.dp),
            text = "Create New",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = text, onValueChange = { text = it },
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
                .fillMaxWidth(),
            label = { Text(text = "Enter name", modifier = Modifier.padding(start = 10.dp))},
            singleLine = true,
            shape = RoundedCornerShape(15.dp)
        )
    }

}