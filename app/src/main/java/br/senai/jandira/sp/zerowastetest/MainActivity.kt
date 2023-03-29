package br.senai.jandira.sp.zerowastetest

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.jandira.sp.zerowastetest.ui.theme.ZeroWasteTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZeroWasteTestTheme() {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(8, 113, 19)
                ) {
                    EntryPage()
                }
            }
        }
    }
}

@Composable
fun EntryPage() {

    val context = LocalContext.current
    val scrollState = rememberScrollState()


    Column(
        modifier = Modifier
            .fillMaxSize(),
//            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(96.dp)
                    .padding(end = 13.dp)
            )
            Text(
                text = stringResource(id = R.string.name_application),
                color = Color.White,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(id = R.string.comunity_question),
                    modifier = Modifier.padding(bottom = 8.dp),
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = stringResource(id = R.string.faca_login),
                    modifier = Modifier.padding(start = 30.dp, end = 30.dp),
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center
                )
            }



//            Button(onClick = {
//                val navigateToHome = Intent(context, HomeActivity::class.java)
//                context.startActivity(navigateToHome)
//            }) {
//                Text(text = "Home")
//            }




            Button(
                onClick = {
                    val navigateToLogIn = Intent(context, LogInActivity::class.java)
                    context.startActivity(navigateToLogIn)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 60.dp, end = 60.dp),
                shape = RoundedCornerShape(40.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(
                        128,
                        204,
                        40
                    )
                )
            ) {
                Text(
                    text = stringResource(id = R.string.login),
                    modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                    color = Color.White,
                    fontSize = 20.sp
                )
            }
            Image(
                painter = painterResource(id = R.drawable.catadores_image),
                contentDescription = "",
                modifier = Modifier.size(width = 300.dp, height = 300.dp)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.not_registered),
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal
                )
                TextButton(onClick = {
                    val navigateToSignIn = Intent(context, SignInActivity::class.java)
                    context.startActivity(navigateToSignIn)
                }) {
                    Text(
                        text = stringResource(id = R.string.register),
                        color = Color(
                            128,
                            204,
                            40
                        ),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ZeroWasteTestTheme() {
        EntryPage()
    }
}