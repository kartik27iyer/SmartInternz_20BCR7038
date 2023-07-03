package com.example.week_two_app

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement import androidx.compose.foundation.layout.Column import androidx.compose.foundation.layout.fillMaxSize import androidx.compose.foundation.layout.fillMaxWidth import androidx.compose.foundation.layout.padding import androidx.compose.material3.Button
import androidx.compose.material3.Text import androidx.compose.runtime.Composable import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource import androidx.compose.ui.text.font.FontWeight import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.vitap_20bcb7043.sharedview.viewModel.sharedview

@Composable
fun details(navController: NavController, viewModel: sharedview)
{
    Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
        .padding(20.dp)

        .fillMaxSize())
    {

        Image(
            painter = painterResource(id = R.drawable.bb), contentDescription = "",
            modifier = Modifier
                .padding(all = 20.dp)
        )
        Text(text="Your Details",modifier= Modifier.padding(20.dp),fontWeight = FontWeight.Bold)
        Text(text = "Name", color = Color.Black,modifier= Modifier.padding(20.dp),fontWeight = FontWeight.Bold)
        Text(text=viewModel.Res.value, color = Color.Black,modifier= Modifier.padding(10.dp))

        Text(text = "Address", color = Color.Black,modifier= Modifier.padding(20.dp),fontWeight = FontWeight.Bold)
        Text(text=viewModel.Res1.value, color = Color.Black,modifier= Modifier.padding(10.dp))
        Text(text = "Age", color = Color.Black,modifier= Modifier.padding(20.dp),fontWeight
        = FontWeight.Bold)
        Text(text=viewModel.Res2.value, color = Color.Black,modifier= Modifier.padding(10.dp))
        Text(text = "Blood type", color = Color.Black,modifier= Modifier.padding(20.dp),fontWeight = FontWeight.Bold)
        Text(text=viewModel.Res3.value, color = Color.Black,modifier= Modifier.padding(10.dp))


        Button(onClick = { navController.navigate(route=Scn.Home.route)
        {popUpTo(Scn.Home.route){ inclusive=true} } }, modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)

        )
        {
            Text(text = "Back", fontWeight = FontWeight.Bold)

        }



    }
}
