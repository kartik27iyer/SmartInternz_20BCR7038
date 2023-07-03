package com.example.bloodbankapplication

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.example.bloodbankapplication.ui.theme.BloodBankApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(modifier = Modifier
                .fillMaxSize()
                .paint(
                    painter = painterResource(id = R.drawable.img),
                    contentScale = ContentScale.Fit
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {


                Box(modifier = Modifier.padding(40.dp)){
                    Column() {
                        Image(painter = painterResource(id = R.drawable.blood),
                            contentDescription = "Blood Logo",
                            modifier = Modifier
                                .width(80.dp)
                                .height(80.dp),
                        alignment = Alignment.Center)

                        Text(text = "UrBLOOD", fontSize = 30.sp, style = TextStyle(color = Color.Black,
                            fontWeight = FontWeight.Black,
                            fontFamily = FontFamily.Monospace,
                            background = Color.White,
                            shadow = Shadow(color = Color.Gray),
                            textAlign = TextAlign.Center))

                        Text(text = "Be A Donar", fontSize = 20.sp, style = TextStyle(color = Color.Black,
                            fontWeight = FontWeight.Black,
                            fontFamily = FontFamily.Monospace,
                            background = Color.White,
                            shadow = Shadow(color = Color.Gray),
                            textAlign = TextAlign.Center))
                    }
                }

                Box(){
                    Textfield1()
                }
                Box(){
                    Textfield2()
                }


                Row(modifier = Modifier.padding(20.dp)) {
                    Column() {
                        ButtonSignUp()
                    }
                    Column(){
                        ButtonSignIn()
                    }
                }

                Text(text = "or SignIn with...", fontSize = 15.sp, style = TextStyle(color = Color.Black,
                    fontWeight = FontWeight.Black,
                    fontFamily = FontFamily.SansSerif,
                    background = Color.White,
                    shadow = Shadow(color = Color.Gray),
                    textAlign = TextAlign.Center))
                Row(modifier = Modifier.padding(10.dp)) {
                    Column() {
                        CircleImageView(painter = painterResource(id = R.mipmap.google))
                    }
                    Column(){
                        CircleImageView(painter = painterResource(id = R.mipmap.facebook))
                    }
                }
            }
            
        }
    }
}

@Composable
fun Textfield1() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        var textInput by remember { mutableStateOf("")}
        OutlinedTextField(value = textInput, onValueChange = {textInput = it},
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            leadingIcon = {Icon(Icons.Outlined.Email, contentDescription = null) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Red,
                unfocusedBorderColor = Gray ,
                focusedLabelColor = Red,
                unfocusedLabelColor = Gray,
                leadingIconColor = Red)
        )
    }
}
@Composable
fun Textfield2() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        var textInput2 by remember { mutableStateOf("")}
        OutlinedTextField(value = textInput2, onValueChange = {textInput2 = it},
            label = { Text("Password")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            leadingIcon = {Icon(Icons.Outlined.Warning, contentDescription = null)},
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Red,
                unfocusedBorderColor = Gray,
                focusedLabelColor = Red,
                unfocusedLabelColor = Gray,
                leadingIconColor = Red)
        )
    }
}

@Composable
fun ButtonSignIn() {
    val mContext = LocalContext.current
    Button(
        onClick = {
            mContext.startActivity(Intent(mContext, Homepage::class.java))
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .padding(5.dp)
            .width(150.dp)
            .height(40.dp)
    ) {
        Text(text = "Sign In", color = Color.White)
    }
}

@Composable
fun ButtonSignUp() {
    Button(
        onClick = {
            //your onclick code
        },
        border = BorderStroke(1.dp, Red),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .padding(5.dp)
            .width(150.dp)
            .height(40.dp)
    ) {
        Text(text = "Sign Up", color = Color.Red)
    }
}

@Composable
fun CircleImageView(painter: Painter) {
    Image(
        painter = painter,
        contentDescription = "Circle Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
            .padding(10.dp)// clip to the circle shape
    )
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Textfield1()
}