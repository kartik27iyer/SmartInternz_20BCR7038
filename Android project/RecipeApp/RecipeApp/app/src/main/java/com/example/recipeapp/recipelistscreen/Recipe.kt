package com.example.recipeapp.recipelistscreen


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.recipeapp.R
import com.example.recipeapp.model.RecipeX
import com.example.recipeapp.viewmodels.HomeScreenViewModel

import kotlinx.coroutines.launch

@Composable
fun Recipe(
    recipe:RecipeX,
    modifier: Modifier = Modifier,
    viewModel : HomeScreenViewModel = hiltViewModel(),
    navController: NavController,
    scaffoldState: ScaffoldState
) {

    val scope = rememberCoroutineScope()

    var savedState by remember(recipe.isSaved) {

        mutableStateOf(recipe.isSaved)
    }
    val preparationTime = recipe.readyInMinutes
    val title = recipe.title



    Card(
            modifier = modifier
                .width(250.dp)
                .height(380.dp)
                .clickable {
                    if (recipe.id != null) {
                        navController.navigate("recipe_detail_screen/${recipe.id}")
                    }
                },
            shape = RoundedCornerShape(20.dp),
            elevation = 5.dp
        ) {

            Box(
                modifier = modifier
                    .fillMaxWidth(0.5f)
                    .fillMaxHeight(0.5f),
                contentAlignment = Alignment.TopStart
            ) {
                val request = ImageRequest.Builder(LocalContext.current)
                    .data(recipe.image)
                    .build()
                val painter = rememberAsyncImagePainter(
                    model = request
                )


                Image(
                    painter = painter,
                    contentDescription = "Recipe Image",
                    modifier = modifier
                        .align(Alignment.TopCenter)
                        .fillMaxWidth()
                        .fillMaxHeight(0.6f),
                    contentScale = ContentScale.Crop
                )


                Box(
                    modifier = Modifier
                        .padding(top = 195.dp, start = 5.dp)
                        .align(Alignment.TopStart)
                        .width(70.dp)
                        .height(30.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(
                            Brush.linearGradient(
                                listOf(Color.Gray, Color.Gray)
                            ), RectangleShape, 0.5f
                        ),
                    contentAlignment = Alignment.Center,
                )
                {
                    Text(
                        text = preparationTime.toString() + "min",
                        fontSize = 15.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }

                val interactionSource = MutableInteractionSource()


                Box(modifier = Modifier
                    .padding(top = 15.dp, start = 205.dp)
                    .align(Alignment.TopStart)
                    .width(40.dp)
                    .height(40.dp)
                    .clickable(
                        interactionSource = interactionSource,
                        null
                    ) {
                        savedState = true
                        recipe.isSaved = true
                    }
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        Brush.linearGradient(
                            listOf(Color.Gray, Color.Gray)
                        ), RectangleShape, 0.5f
                    ),
                    contentAlignment = Alignment.Center
                ) {
                    val path = remember {
                        Path()
                    }
                    if (!savedState) {
                        Canvas(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 10.dp, start = 12.dp)
                                .clickable {
                                    scope.launch {
                                        savedState = true
                                        scaffoldState.snackbarHostState.showSnackbar(
                                            message = "Recipe Saved",
                                            actionLabel = "View"
                                        )
                                    }
                                }
                        ) {

                            path.moveTo(0f, 0f)
                            path.lineTo(30f, 0f)
                            path.moveTo(30f, 0f)
                            path.lineTo(30f, 40f)
                            path.quadraticBezierTo(30f, 50f, 15f, 25f)
                            path.moveTo(15f, 25f)
                            path.quadraticBezierTo(0f, 50f, 0f, 40f)
                            path.moveTo(0f, 40f)
                            path.lineTo(0f, 0f)
                            path.moveTo(0f, 0f)
                            drawPath(path, color = Color.Black, style = Stroke(2.dp.toPx()))

                        }
                    }
                    Box {
                        AnimatedVisibility(visible = savedState) {

                            val interactionSource = MutableInteractionSource()
                            Image(
                                painter = painterResource(id = com.example.recipeapp.R.drawable.saved),
                                contentDescription = "Saved Button",
                                modifier = Modifier
                                    .height(40.dp)
                                    .width(25.dp)
                                    .clickable(
                                        interactionSource = interactionSource,
                                        null
                                    ) {
                                        savedState = false
                                        recipe.isSaved = false
                                    }
                            )
                        }
                    }
                }

            }
            val rating by remember {
                mutableStateOf(recipe.veryPopular)
            }
            var ratingInt by remember {
                mutableStateOf(0)
            }
            if (ratingInt == 0) {
                if (!rating) {
                    ratingInt = (1..3).random()
                } else {
                    ratingInt = (3..5).random()
                }
            }
            RatingBar(
                modifier = Modifier.padding(top = 110.dp), rating = ratingInt
            )
            Spacer(modifier = Modifier.height(30.dp))
            Column(modifier = Modifier.padding(top = 270.dp)) {

                Text(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .height(30.dp),
                    text = title,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Black
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, top = 25.dp)
                        .height(40.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(color = Color.White)
                        .border(2.dp, Color.Black, RoundedCornerShape(20.dp)),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = "Add ingredients to cart",
                        fontSize = 16.sp,
                        color = Color.Black,
                    )

                }
            }
        }
}




@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating : Int
) {

    var ratingState by remember {
        mutableStateOf(rating)
    }
    
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        for(i in 1..5){
            Icon(
                painter = painterResource(id = R.drawable.star),
                contentDescription = "",
                modifier = modifier
                    .width(15.dp)
                    .height(15.dp),
                tint = if(i <= ratingState) Color(0XFFFFD700) else Color(0XFFA2ADB1)

            )
        }
    }


}