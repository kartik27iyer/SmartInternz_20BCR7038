package com.example.recipeapp.savedrecipescreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import com.example.recipeapp.model.RecipeDetail
import com.example.recipeapp.model.RecipeX
import com.example.recipeapp.viewmodels.HomeScreenViewModel
import kotlinx.coroutines.launch

@Composable
fun SavedRecipeItem(recipe: RecipeDetail,
                    modifier: Modifier = Modifier,
                    viewModel : HomeScreenViewModel = hiltViewModel(),
                    navController: NavController,
                    scaffoldState: ScaffoldState
) {

    val scope = rememberCoroutineScope()


    val preparationTime = recipe.readyInMinutes
    val title = recipe.title



    Card(
        modifier = modifier
            .width(250.dp)
            .height(260.dp)
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
                    .padding(top = 115.dp, start = 5.dp)
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
                Box {

                        val interactionSource = MutableInteractionSource()
                        Image(
                            painter = painterResource(id = R.drawable.saved),
                            contentDescription = "Saved Button",
                            modifier = Modifier
                                .height(40.dp)
                                .width(25.dp)
                                .clickable(
                                    interactionSource = interactionSource,
                                    null
                                ) {

                                }
                        )

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
            modifier = Modifier.padding(top = 80.dp), rating = ratingInt
        )
        Spacer(modifier = Modifier.height(30.dp))
        Column(modifier = Modifier.padding(top = 190.dp)) {

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