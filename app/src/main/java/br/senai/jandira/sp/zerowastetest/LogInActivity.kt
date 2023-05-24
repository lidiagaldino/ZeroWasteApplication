package br.senai.jandira.sp.zerowastetest

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import br.senai.jandira.sp.zerowastetest.api.ApiCalls
import br.senai.jandira.sp.zerowastetest.api.RetrofitApi
import br.senai.jandira.sp.zerowastetest.dataSaving.SessionManager
import br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.LoginResponse
import br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.UserLoginRequest
import br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelGeocode.Geometry
import br.senai.jandira.sp.zerowastetest.ui.theme.ZeroWasteTestTheme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.CompletableFuture

class LogInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ZeroWasteTestTheme() {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    LogInActivityBody(this)
                }
            }
        }
    }
}

@Composable
fun LogInActivityBody(activity: Activity) {

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    var fusedLocationProviderClient: FusedLocationProviderClient

    val retrofit = RetrofitApi.getMainApi()
    val apiCalls = retrofit.create(ApiCalls::class.java)
    val sessionManager = SessionManager(context)


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
        mutableStateOf(sessionManager.getUserEmail())
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

    var emailError by remember {
        mutableStateOf(false)
    }
    var authError by remember {
        mutableStateOf(false)
    }
    var passEmpty by remember {
        mutableStateOf(false)
    }



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
                    .height(500.dp)
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
                                contentDescription = "Email"
                            )
                        },
                        trailingIcon = {
                            if (emailError)
                                       Icon(imageVector = Icons.Default.Error, contentDescription = "E-mail Error")
                            else
                                null
                        },
                        isError = emailError || authError,
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
                    if (emailError) {
                        Text(
                            text = stringResource(id = R.string.empty_error),
                            color = Color.Red,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 30.dp, start = 30.dp),
                            textAlign = TextAlign.End
                        )
                        Spacer(modifier = Modifier.height(7.dp))
                    } else {
                        Spacer(modifier = Modifier.height(15.dp))
                    }

                    OutlinedTextField(
                        value = passwordState,
                        onValueChange = { newValue ->
                            passwordState = newValue
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 30.dp, end = 30.dp)
                            .background(color = Color.White, shape = RoundedCornerShape(10.dp)),
                        placeholder = { Text(text = stringResource(id = R.string.password_label)) },
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
                        isError = authError || passEmpty,
                        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                focusManager.clearFocus()
                            }
                        ),
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
                    if (authError) {
                        Text(
                            text = stringResource(id = R.string.failed_auth_message),
                            color = Color.Red,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 30.dp, start = 30.dp),
                            textAlign = TextAlign.End
                        )
                        Spacer(modifier = Modifier.height(7.dp))
                    } else if (passEmpty){
                        Text(
                            text = stringResource(id = R.string.empty_error),
                            color = Color.Red,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 30.dp, start = 30.dp),
                            textAlign = TextAlign.End
                        )
                        Spacer(modifier = Modifier.height(7.dp))
                    } else {
                        Spacer(modifier = Modifier.height(15.dp))
                    }
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

                            emailError = emailState.isEmpty()
                            authError = passwordState.isEmpty()

                            if (emailState.isNotEmpty() && passwordState.isNotEmpty()) {
                                val login = UserLoginRequest(
                                    email = emailState,
                                    senha = passwordState
                                )

                                fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity)

                                fun fetchLocation(): CompletableFuture<Geometry> {
                                    val completableFuture = CompletableFuture<Geometry>()

                                    if (ContextCompat.checkSelfPermission(
                                            context,
                                            Manifest.permission.ACCESS_FINE_LOCATION
                                        ) != PackageManager.PERMISSION_GRANTED &&
                                        ContextCompat.checkSelfPermission(
                                            context,
                                            Manifest.permission.ACCESS_COARSE_LOCATION
                                        ) != PackageManager.PERMISSION_GRANTED
                                    ) {
                                        ActivityCompat.requestPermissions(
                                            activity,
                                            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                                            101
                                        )
                                    }

                                    fusedLocationProviderClient.lastLocation
                                        .addOnSuccessListener { location ->
                                            if (location != null) {
                                                val latLong = Geometry(
                                                    lat = location.latitude,
                                                    lng = location.longitude
                                                )
                                                completableFuture.complete(latLong)
                                            } else {
                                                // Caso não seja possível obter a localização
                                                completableFuture.complete(null)
                                            }
                                        }
                                        .addOnFailureListener { e ->
                                            // Em caso de erro ao obter a localização
                                            completableFuture.completeExceptionally(e)
                                        }

                                    return completableFuture
                                }

                                val userLogin = apiCalls.verifyLogin(login)

                                userLogin.enqueue(object : Callback<LoginResponse> {
                                    override fun onResponse(
                                        call: Call<LoginResponse>,
                                        response: Response<LoginResponse>
                                    ) {

                                        if (response.body() != null) {

                                            val authToken = response.body()!!.token

                                            if (authToken != "" && authToken != null) {

                                                Log.i("success", authToken)
                                                sessionManager.saveAuthToken(authToken)
                                                sessionManager.saveUserLogin(emailState)
                                                val intent =
                                                    Intent(context, HomeActivity::class.java)
                                                context.startActivity(intent)
                                            } else
                                                Log.i("fail", "erro ao fazer login")

                                        } else {
                                            authError = true
                                            Log.i("failed authentication", response.toString())
                                        }
                                    }

                                    override fun onFailure(
                                        call: Call<LoginResponse>,
                                        t: Throwable
                                    ) {
                                        Log.i("fail", t.message.toString())
                                    }
                                })
                            }

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