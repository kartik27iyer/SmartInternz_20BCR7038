package com.example.recipeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.recipeapp.model.BottomNavItem
import com.example.recipeapp.recipedetailscreen.RecipeDetail
import com.example.recipeapp.recipelistscreen.IndianRecipeList
import com.example.recipeapp.recipelistscreen.RecipeListScreen
import com.example.recipeapp.recipelistscreen.VeganRecipeList
import com.example.recipeapp.savecookbookscreen.SaveCookBook
import com.example.recipeapp.savedrecipescreen.SavedScreen
import com.example.recipeapp.ui.theme.RecipeAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipeAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val navController = rememberNavController()
                    val scaffoldState = rememberScaffoldState()

                    var showBottomBar by rememberSaveable { mutableStateOf(true) }
                    val navBackStackEntry by navController.currentBackStackEntryAsState()

                    showBottomBar = when (navBackStackEntry?.destination?.route) {
                        "recipe_list_screen" -> true // on this screen bottom bar should be shown
                        "saved_recipes" -> true // here too
                        else -> false // in all other cases don't show bottom bar
                    }
                    Scaffold(

                        scaffoldState = scaffoldState,
                        bottomBar = if(showBottomBar) {
                            {
                                BottomNavigationBar(
                                    items = listOf(
                                        BottomNavItem(
                                            name = "HOME",
                                            route = "recipe_list_screen",
                                            icon = Icons.Default.Home

                                        ),
                                        BottomNavItem(
                                            name = "SAVED",
                                            route = "saved_recipes",
                                            icon = Icons.Default.List
                                        )
                                    ),
                                    navController = navController,
                                    onItemClick = {
                                        navController.navigate(it.route)
                                    }
                                )
                            }
                        }else {
                            {}
                              },
                        topBar = if(showBottomBar){{
                            TopAppBar(
                                title = { Text(text = "RECIPE APP", color = Color.DarkGray, fontSize = 25.sp, fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold)},
                                navigationIcon = {
                                    IconButton(onClick = { /*TODO*/ }) {
                                        Image(painterResource(id = R.drawable.logo ) ,
                                            contentDescription ="logo",
                                            modifier = Modifier.size(120.dp) )
                                    }
                                },
                                backgroundColor = Color.White,
                                contentColor = Color.Black,
                                elevation = 5.dp,
                                actions = {
                                    IconButton(onClick = { /*TODO*/ }) {
                                        Box(
                                            modifier = Modifier
                                                .width(50.dp)
                                                .padding(end = 10.dp)
                                                .background(
                                                    Color.Black,
                                                    RoundedCornerShape(10.dp)
                                                ),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.ShoppingCart,
                                                contentDescription = "Shopping Cart",
                                                modifier = Modifier.background(
                                                    Color.Black,
                                                    RoundedCornerShape(10.dp)
                                                ).size(40.dp),
                                                tint = Color.White
                                            )
                                        }
                                    }
                                }
                            )

                        }}else{{
                            
                        }}
                    ) { paddingValues ->


                        NavHost(
                            navController = navController,
                            startDestination = "recipe_list_screen"
                        ) {
                            composable("recipe_list_screen") {

                                Column(
                                    modifier = Modifier
                                        .verticalScroll(rememberScrollState())
                                        .padding(paddingValues)
                                ) {
                                    RecipeListScreen(
                                        navController = navController,
                                        scaffoldState = scaffoldState
                                    )
                                    VeganRecipeList(
                                        navController = navController,
                                        scaffoldState = scaffoldState
                                    )
                                    IndianRecipeList(
                                        navController = navController,
                                        scaffoldState = scaffoldState
                                    )
                                }

                            }
                            composable("saved_recipes") {
                                SavedScreen(navController = navController,scaffoldState = scaffoldState)
                            }
                            composable("insert_cookbook_screen"){
                                SaveCookBook(scaffoldState = scaffoldState, navController = navController)
                            }
                            composable("recipe_detail_screen/{recipeId}",
                                arguments = listOf(
                                    navArgument("recipeId") {
                                        type = NavType.IntType
                                    }
                                )
                            ) {

                                val recipeId = remember {
                                    it.arguments?.getInt("recipeId")
                                }
                                RecipeDetail(recipeId!!, scaffoldState = scaffoldState){
                                    navController.navigate("insert_cookbook_screen")
                                }
                            }
                        }
                    }

                }
            }
        }
    }


}


@Composable
fun BottomNavigationBar(
    items : List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick : (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.DarkGray,
//        elevation = 5.dp
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                modifier = Modifier.clip(RoundedCornerShape(10.dp)),
                selected = selected,
                onClick = { onItemClick(item) },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black,

                icon = {
                    val boxColor = if (selected) Color.White else Color.White
                    val boxWidth by animateDpAsState(targetValue = if(selected) 150.dp else 40.dp, spring(Spring.DampingRatioMediumBouncy)
                    )
                    Card(
                        modifier = Modifier
                            .width(boxWidth)
                          ,
                        backgroundColor = boxColor,
                        shape = RoundedCornerShape(10.dp),
                    )
                    {
                        Row(modifier = Modifier.padding(10.dp),horizontalArrangement = Arrangement.SpaceEvenly) {
                            Icon(imageVector = item.icon, contentDescription = item.name)
                            Spacer(modifier = Modifier.width(15.dp))
                            if (selected) {
                                Text(
                                    text = item.name,
                                    textAlign = TextAlign.Center,
                                    fontSize = 15.sp
                                )
                            }
                        }
                    }
                })
        }

    }
}

fun NavGraphBuilder.DetailsGraph(navController: NavController){
    navigation(startDestination = "recipe_detail_screen/{recipeId}",route = "recipe_detail_screen/{recipeId}"){

    }
}




