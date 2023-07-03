package com.example.week_two_app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.week_two_app.sharedview.viewModel.sharedview

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class) @Composable
fun Home(
    navController: NavController, viewModel: sharedview
) {
    var laz by remember {
        mutableStateOf("")
    }
    var laz1 by remember {
        mutableStateOf("")
    }
    var laz2 by remember {
        mutableStateOf("")
    }
    var laz3 by remember {
        mutableStateOf("")
    }
    var dataList = remember {
        mutableStateListOf<String>("")
    }



    Column(
        verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .padding(20.dp)

            .fillMaxSize()
            .background(Color.White)

    ) {

        Image(
            painter = painterResource(id = R.drawable.oi), contentDescription = "",
            modifier = Modifier
                .padding(all = 20.dp)
        )
        Text(text = "Enter your Name", color = Color.Black) TextField(
                value = laz, onValueChange = { laz = it }, modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        )
        Text(text = "Address", color = Color.Black) TextField(
                value = laz1, onValueChange = { laz1 = it }, modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        )
        Text(text = "Age", color = Color.Black) TextField(
                value = laz2, onValueChange = { laz2 = it }, modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        )
        Text(text = "Blood type", color = Color.Black) TextField(
                value = laz3, onValueChange = { laz3 = it }, modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        )


        Button(
            onClick = { viewModel.setdata(laz) viewModel.setdata1(laz1) viewModel.setdata2(laz2) viewModel.setdata3(laz3)

                navController.navigate(route = Scn.Details.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)


        ) {
            Text(text = "Submit")
        }

    }

}
