package br.senai.jandira.sp.zerowastetest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.jandira.sp.zero_wasteapplication.api.ApiCalls
import br.senai.jandira.sp.zero_wasteapplication.api.RetrofitApi
import br.senai.jandira.sp.zero_wasteapplication.constants.Constants
import br.senai.jandira.sp.zerowastetest.model.LoginResponse
import br.senai.jandira.sp.zerowastetest.model.UserLoginRequest
import br.senai.jandira.sp.zerowastetest.ui.theme.ZeroWasteTestTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZeroWasteTestTheme() {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    LogInActivityBody()
                }
            }
        }
    }
}

@Preview
@Composable
fun LogInActivityBody() {

    val context = LocalContext.current

    val retrofit = RetrofitApi.getRetrofit(Constants.API_URL)
    val apiCalls = retrofit.create(ApiCalls::class.java)


    var recicladorClick by remember {
        mutableStateOf(true)
    }

    var catadorClick by remember {
        mutableStateOf(false)
    }

    var color1 by remember {
        mutableStateOf(Color(128, 204, 40))
    }
    var color2 by remember {
        mutableStateOf(Color.Transparent)
    }

    var emailState by remember {
        mutableStateOf("")
    }

    var passwordState by remember {
        mutableStateOf("")
    }

    var passwordVisibility by remember {
        mutableStateOf(false)
    }

    val icon =
        if (passwordVisibility)
            painterResource(id = R.drawable.visibility_icon_on)
        else
            painterResource(id = R.drawable.visibility_icon_off)

    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 7.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.back_arrow),
                contentDescription = "Voltar",
                modifier = Modifier
                    .size(50.dp)
                    .padding(start = 16.dp, top = 16.dp)
                    .clickable {
                        val intent = Intent(context, MainActivity::class.java)
                        context.startActivity(intent)
                    }
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.app_logo),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .size(96.dp)
                        .padding(start = 8.dp, end = 8.dp)
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.Black)) {
                            append("Zero")
                        }
                        append(' ')
                        withStyle(style = SpanStyle(color = Color(8, 113, 19))) {
                            append("Waste")
                        }
                    },
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold
                )
            }

        }
        Column(
            modifier = Modifier.fillMaxHeight(0.8f),
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .height(450.dp)
                    .padding(start = 25.dp, end = 25.dp),
                shape = RoundedCornerShape(20.dp),
                backgroundColor = Color(8, 113, 19)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 40.dp, bottom = 15.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Row(
                            modifier = Modifier.background(
                                color = Color.White, shape = RoundedCornerShape(7.dp)
                            ), verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = stringResource(id = R.string.recycler),
                                modifier = Modifier
                                    .background(
                                        color1,
                                        shape = RoundedCornerShape(
                                            topStart = 7.dp,
                                            bottomStart = 7.dp
                                        )
                                    )
                                    .clickable {
                                        recicladorClick = true
                                        catadorClick = false
                                        color1 = Color(128, 204, 40)
                                        color2 = Color.Transparent
                                    }
                                    .padding(
                                        top = 15.dp, bottom = 15.dp
                                    )
                                    .width(130.dp),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center)
                            Text(text = stringResource(
                                id = R.string.catador
                            ),
                                modifier = Modifier
                                    .background(
                                        color2,
                                        shape = RoundedCornerShape(topEnd = 7.dp, bottomEnd = 7.dp)
                                    )
                                    .clickable {
                                        recicladorClick = false
                                        catadorClick = true
                                        color2 = Color(128, 204, 40)
                                        color1 = Color.Transparent
                                    }
                                    .padding(
                                        top = 15.dp,
                                        bottom = 15.dp,
                                    )
                                    .width(130.dp),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    Text(
                        text = stringResource(id = R.string.welcome_message),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp, bottom = 5.dp),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = emailState, onValueChange = { newValue ->
                            emailState = newValue
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 30.dp, end = 30.dp)
                            .background(color = Color.White, shape = RoundedCornerShape(10.dp)),
                        placeholder = { Text(text = stringResource(id = R.string.email_label)) },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = ""
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        singleLine = true,
                        shape = RoundedCornerShape(10.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = colorResource(
                                id = R.color.light_green
                            ),
                            cursorColor = colorResource(
                                id = R.color.dark_green
                            )
                        )
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    OutlinedTextField(
                        value = passwordState,
                        onValueChange = { newValue ->
                            passwordState = newValue
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 30.dp, end = 30.dp)
                            .background(color = Color.White, shape = RoundedCornerShape(10.dp)),
                        placeholder = {
                            Text(text = stringResource(id = R.string.password_label))
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = ""
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = {
                                passwordVisibility = !passwordVisibility
                            }) {
                                Icon(
                                    painter = icon,
                                    contentDescription = "visibility icon",
                                    modifier = Modifier.width(35.dp)
                                )
                            }
                        },
                        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        singleLine = true,
                        shape = RoundedCornerShape(10.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = colorResource(
                                id = R.color.light_green
                            ),
                            cursorColor = colorResource(
                                id = R.color.dark_green
                            )
                        )
                    )
                    TextButton(
                        onClick = {
                            //Esqueceu a senha parte
                        },
                        modifier = Modifier.height(35.dp),
                    ) {
                        Text(
                            text = stringResource(id = R.string.forgot_pass),
                            color = Color.White,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Normal,
                            style = TextStyle(textDecoration = TextDecoration.Underline)
                        )
                    }
                    Spacer(modifier = Modifier.height(50.dp))
                    Button(
                        onClick = {
                            val login = UserLoginRequest(
                                email = emailState,
                                senha = passwordState
                            )

                            val userLogin = apiCalls.verifyLogin(login)

                            userLogin.enqueue(object : Callback<LoginResponse> {
                                override fun onResponse(
                                    call: Call<LoginResponse>,
                                    response: Response<LoginResponse>
                                ) {
                                    Log.i("success", response.body()!!.token)
                                }

                                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                                    Log.i("fail", t.message.toString())
                                }
                            })

                        },
                        modifier = Modifier
                            .padding(start = 30.dp, end = 30.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(128, 204, 40))
                    ) {
                        Text(
                            text = (stringResource(id = R.string.login)),
                            modifier = Modifier
                                .fillMaxWidth(),
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview3() {
    ZeroWasteTestTheme() {
        LogInActivityBody()
    }
}