package br.senai.jandira.sp.zerowastetest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.jandira.sp.zero_wasteapplication.api.ApiCalls
import br.senai.jandira.sp.zero_wasteapplication.api.RetrofitApi
import br.senai.jandira.sp.zerowastetest.constants.Constants
import br.senai.jandira.sp.zerowastetest.dataSaving.SessionManager
import br.senai.jandira.sp.zerowastetest.model.UserData
import br.senai.jandira.sp.zerowastetest.ui.theme.ZeroWasteTestTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileActivityGerador : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ZeroWasteTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ProfileActivityBody()
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ProfileActivityBody() {

    val context = LocalContext.current
    val scrollable = rememberScrollState()

    val retrofit = RetrofitApi.getRetrofit(Constants.API_URL, context)
    val apiCalls = retrofit.create(ApiCalls::class.java)

    val sessionManager = SessionManager(context)
    val authToken = sessionManager.fetchAuthToken()

//    Log.i("token", authToken.toString())

    var menuVisibility by remember {
        mutableStateOf(false)
    }

    var actionOptionsVisibility by remember {
        mutableStateOf(false)
    }

    var requestPickupClick by remember {
        mutableStateOf(false)
    }
    var registerLocationClick by remember {
        mutableStateOf(false)
    }
    var mapCatadoresClick by remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.top_profile_rectangle),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.TopCenter
        )
    }


    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.borgor),
            contentDescription = "",
            modifier = Modifier
                .size(50.dp)
                .padding(start = 15.dp, top = 15.dp)
                .clickable {
                    menuVisibility = !menuVisibility
                }
        )
        Button(onClick = {
            val userInfo = apiCalls.getUserData(authToken.toString())
            userInfo.enqueue(object : Callback<UserData> {
                override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                    if (response.body() != null)
                        Log.i("success", response.body()!!.id)
                    else
                        Log.i("late fail", response.body().toString())

                }

                override fun onFailure(call: Call<UserData>, t: Throwable) {
                    Log.i("failed", t.message.toString())
                }

            })
        }) {

        }
    }
    AnimatedVisibility(visible = menuVisibility,
        enter = slideInHorizontally(animationSpec = tween(400)) { fullWidth -> -fullWidth } + fadeIn(
            animationSpec = tween(durationMillis = 200)
        ),
        exit = slideOut(tween(100, easing = FastOutSlowInEasing)) {
            IntOffset(-180, 0)
        } + fadeOut(animationSpec = tween(durationMillis = 200))
    ) {
        Card(
            modifier = Modifier
                .fillMaxHeight()
                .padding(end = 100.dp)
                .verticalScroll(scrollable),
            backgroundColor = colorResource(id = R.color.almost_white)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Image(painter = painterResource(id = R.drawable.back_arrow),
                    contentDescription = "Voltar",
                    modifier = Modifier
                        .size(40.dp)
                        .padding(start = 15.dp, top = 15.dp)
                        .clickable {
                            menuVisibility = !menuVisibility
                        }
                )
                Spacer(modifier = Modifier.height(20.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp)
                        .clickable {
                            val intent = Intent(context, ProfileActivityGerador::class.java)
                            context.startActivity(intent)
                        },
                    shape = RoundedCornerShape(10.dp),
                    backgroundColor = colorResource(id = R.color.dark_green)
                ) {
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.avatar_standard_icon),
                            contentDescription = "Foto do Perfil",
                            modifier = Modifier
                                .size(60.dp)
                                .padding(4.dp)
                        )
                        Column(
                            modifier = Modifier.padding(start = 4.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Nome do usuário",
                                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp),
                                color = Color.White,
                                fontSize = 18.sp
                            )
                            Text(text = "Conectado", color = Color.White, fontSize = 14.sp)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                        .padding(start = 8.dp, end = 20.dp)
                        .clickable {
                            menuVisibility = !menuVisibility
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.home_icon),
                        contentDescription = "Página inicial",
                        modifier = Modifier
                            .size(55.dp)
                            .padding(start = 12.dp, end = 4.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.home_page_menu),
                        modifier = Modifier.padding(start = 4.dp, end = 20.dp),
                        fontSize = 18.sp
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(start = 10.dp, end = 20.dp)
                        .clickable {
                            actionOptionsVisibility = !actionOptionsVisibility
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.actions_icon),
                        contentDescription = "Ações",
                        modifier = Modifier
                            .size(60.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(id = R.string.actions_text_menu),
                            fontSize = 18.sp
                        )
                        Image(
                            painter = painterResource(id = R.drawable.drop_down_icon),
                            contentDescription = "Mais ações",
                            modifier = Modifier
                                .size(15.dp)
                        )
                    }
                }
                AnimatedVisibility(
                    visible = actionOptionsVisibility,
                    modifier = Modifier.padding(start = 20.dp),
                    enter = slideInVertically(
                        initialOffsetY = { -40 }
                    ) + expandVertically(
                        expandFrom = Alignment.Top
                    ) + fadeIn(initialAlpha = 0.3f),
                    exit = slideOutVertically() + shrinkVertically() + fadeOut() + scaleOut(
                        targetScale = 1.2f
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(
                            start = 10.dp,
                            top = 10.dp,
                            end = 20.dp
                        )
                    ) {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp, bottom = 10.dp)
                            .clickable { requestPickupClick = !requestPickupClick }) {
                            Text(
                                text = stringResource(id = R.string.request_pickup),
                                color = verifyClick(
                                    getClick = requestPickupClick
                                )
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp, bottom = 10.dp)
                                .clickable { registerLocationClick = !registerLocationClick }) {
                            Text(
                                text = stringResource(id = R.string.register_pickup_location),
                                color = verifyClick(
                                    getClick = registerLocationClick
                                )
                            )

                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp, bottom = 10.dp)
                                .clickable { mapCatadoresClick = !mapCatadoresClick }) {
                            Text(
                                text = stringResource(id = R.string.map_close_catadores),
                                color = verifyClick(
                                    getClick = mapCatadoresClick
                                )
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                        .padding(start = 8.dp, end = 20.dp)
                        .clickable {
                            /*TODO*/
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.prize_icon),
                        contentDescription = "Página inicial",
                        modifier = Modifier
                            .size(55.dp)
                            .padding(start = 12.dp, end = 4.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.prize_text_menu),
                        modifier = Modifier.padding(start = 4.dp, end = 20.dp),
                        fontSize = 18.sp
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                        .padding(start = 8.dp, end = 20.dp)
                        .clickable {
                            /*TODO*/
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.hints_icon),
                        contentDescription = "Dicas",
                        modifier = Modifier
                            .size(55.dp)
                            .padding(start = 12.dp, end = 4.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.hints_menu),
                        modifier = Modifier.padding(start = 4.dp, end = 20.dp),
                        fontSize = 18.sp
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                        .padding(start = 8.dp, end = 20.dp)
                        .clickable {
                            /*TODO*/
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.favorites_icon),
                        contentDescription = "Favoritos",
                        modifier = Modifier
                            .size(55.dp)
                            .padding(start = 12.dp, end = 4.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.favorites_menu),
                        modifier = Modifier.padding(start = 4.dp, end = 20.dp),
                        fontSize = 18.sp
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                        .padding(start = 10.dp, end = 20.dp)
                        .clickable {
                            val intent = Intent(context, MainActivity::class.java)
                            context.startActivity(intent)
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.log_out_icon),
                        contentDescription = "Sair",
                        modifier = Modifier
                            .size(55.dp)
                            .padding(start = 12.dp, end = 4.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.logout_menu),
                        modifier = Modifier.padding(start = 4.dp, end = 20.dp),
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview4() {
    ZeroWasteTestTheme {
        ProfileActivityBody()
    }
}