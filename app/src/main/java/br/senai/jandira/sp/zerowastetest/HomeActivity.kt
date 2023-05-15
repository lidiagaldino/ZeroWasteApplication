package br.senai.jandira.sp.zerowastetest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.jandira.sp.zerowastetest.api.ApiCalls
import br.senai.jandira.sp.zerowastetest.api.RetrofitApi
import br.senai.jandira.sp.zerowastetest.dataSaving.SessionManager
import br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.UserData
import br.senai.jandira.sp.zerowastetest.ui.theme.ZeroWasteTestTheme
import coil.compose.rememberImagePainter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZeroWasteTestTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    HomeContent()
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeContent() {

    val scrollable = rememberScrollState()

    val context = LocalContext.current

    val retrofit = RetrofitApi.getMainApi()
    val apiCalls = retrofit.create(ApiCalls::class.java)
    val sessionManager = SessionManager(context)

    var authToken = "Bearer " + sessionManager.fetchAuthToken()

    var dadosUsuario by remember {
        mutableStateOf(UserData())
    }
    var username by remember {
        mutableStateOf("...")
    }
    var userType by remember {
        mutableStateOf("...")
    }
    
    var profilePicture by remember {
        mutableStateOf("")
    }

    val userInfo = apiCalls.getUserData(authToken).enqueue(object : Callback<UserData> {
        override fun onResponse(call: Call<UserData>, response: Response<UserData>) {

            if(response.body() == null){

                val backToMain = Intent(context, MainActivity::class.java)
                context.startActivity(backToMain)

            } else {

                Log.i("tesiting", response.code().toString())

                dadosUsuario = response.body()!!
                username = if (dadosUsuario.pessoa_fisica!!.isEmpty())
                    dadosUsuario.pessoa_juridica!![0].nome_fantasia
                else
                    dadosUsuario.pessoa_fisica!![0].nome

                userType = if (dadosUsuario.catador!!.isEmpty())
                    "Gerador"
                else
                    "Catador"

                sessionManager.saveUserId(dadosUsuario.id)
                
                profilePicture = dadosUsuario.foto
            }

        }

        override fun onFailure(call: Call<UserData>, t: Throwable) {
            Log.i("fail", t.message.toString())
        }
    })

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

    Image(
        painter = painterResource(id = R.drawable.ellipse_top_home),
        contentDescription = "",
        modifier = Modifier
            .padding(start = 215.dp)
            .blur(radius = blurEffect(menuVisibility)),
        alignment = Alignment.TopEnd
    )
    Image(
        painter = painterResource(id = R.drawable.ellipse_bottom_home),
        contentDescription = "",
        modifier = Modifier
            .padding(end = 260.dp)
            .blur(radius = blurEffect(menuVisibility)),
        alignment = Alignment.BottomStart
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .blur(blurEffect(menuVisibility))
            .verticalScroll(scrollable)
    ) {
        Image(
            painter = painterResource(id = R.drawable.borgor),
            contentDescription = "",
            modifier = Modifier
                .size(40.dp)
                .padding(start = 15.dp, top = 15.dp)
                .clickable {
                    menuVisibility = !menuVisibility
                }
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(65.dp)
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color(8, 113, 19))) {
                        append("Zero")
                    }
                    append(' ')
                    withStyle(style = SpanStyle(color = Color.Black)) {
                        append("Waste")
                    }
                }, fontSize = 26.sp, fontWeight = FontWeight.Bold
            )
        }
        Image(
            painter = painterResource(id = R.drawable.lata_melhor),
            contentDescription = "",
            modifier = Modifier.size(380.dp),
            alignment = Alignment.Center
        )
        Text(
            text = stringResource(id = R.string.lorem_ipsum),
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 12.dp, end = 12.dp)
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    val toPagerSlider = Intent(context, PageSliderActivity::class.java)
                    context.startActivity(toPagerSlider)
                },
                border = BorderStroke(2.dp, color = Color.White),
                modifier = Modifier.padding(start = 25.dp, top = 20.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(8, 113, 19))
            ) {
                Text(
                    text = stringResource(id = R.string.learn_more),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
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
                            val intent = Intent(context, MyProfileActivity::class.java)
                            context.startActivity(intent)
                        },
                    shape = RoundedCornerShape(10.dp),
                    backgroundColor = colorResource(id = R.color.dark_green)
                ) {
                    Row {

                        DisplayImageFromUrl(imageUrl = profilePicture, "Foto de perfil", size = 60.dp, padding = 4.dp)

//                        Image(
//                            painter = DisplayImageFromUrl(imageUrl = profilePicture),
//                            contentDescription = "Foto do Perfil",
//                            modifier = Modifier
//                                .size(60.dp)
//                                .padding(4.dp)
//                        )

                        Column(
                            modifier = Modifier.padding(start = 4.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = username,
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
                                .clickable {
                                    registerLocationClick = !registerLocationClick
                                    val toCadastrarEnd = Intent(context, CadastrarEnd::class.java)
                                    context.startActivity(toCadastrarEnd)
                                }) {
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
                            sessionManager.eraseAuthToken()
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

fun blurEffect(menuVisibility: Boolean): Dp {
    return if (menuVisibility) {
        10.dp
    } else {
        0.dp
    }
}

@Composable
fun verifyClick(getClick: Boolean): Color {
    return if (getClick)
        colorResource(id = R.color.light_green)
    else
        colorResource(id = R.color.black)
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomePagePreview() {
    HomeContent()
}

@Composable
fun DisplayImageFromUrl(imageUrl: String, description: String, size: Dp, padding:Dp) {
    val painter = rememberImagePainter(imageUrl)
    Column{
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .size(size)
                .padding(padding)
                .clip(CircleShape)
        )
    }
}