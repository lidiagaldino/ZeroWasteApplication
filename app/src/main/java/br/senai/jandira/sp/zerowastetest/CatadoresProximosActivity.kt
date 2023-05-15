package br.senai.jandira.sp.zerowastetest

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.jandira.sp.zerowastetest.api.ApiCalls
import br.senai.jandira.sp.zerowastetest.api.RetrofitApi
import br.senai.jandira.sp.zerowastetest.dataSaving.SessionManager
import br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.CatadoresProximos
import br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.UserData
import br.senai.jandira.sp.zerowastetest.ui.theme.ZeroWasteTestTheme
import com.google.android.gms.common.api.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CatadoresProximosActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZeroWasteTestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(8, 113, 19),
                ) {
                    CatadoresProximosContent()
                }
            }
        }
    }
}

@Composable
fun CatadoresProximosContent() {

    val context = LocalContext.current

    val retrofit = RetrofitApi.getMainApi()
    val apiCalls = retrofit.create(ApiCalls::class.java)
    val sessionManager = SessionManager(context)

    var authToken = "Bearer " + sessionManager.fetchAuthToken()

    var catadores by remember {
        mutableStateOf(listOf<CatadoresProximos>())
    }

    apiCalls.getUserData(authToken).enqueue(object : Callback<UserData> {
        override fun onResponse(call: Call<UserData>, response: Response<UserData>) {

            val dadosUsuario = response.body()!!

            apiCalls.catadoresProximos(authToken, dadosUsuario.endereco_usuario!![0].id_endereco!!).enqueue(
                object : Callback<CatadoresProximos> {
                    override fun onResponse(
                        call: Call<CatadoresProximos>,
                        response: Response<CatadoresProximos>
                    ) {
                        TODO("Not yet implemented")
                    }

                    override fun onFailure(call: Call<CatadoresProximos>, t: Throwable) {
                        Log.i("fail_catadoresProximos", t.message.toString())
                    }

                })
        }

        override fun onFailure(call: Call<UserData>, t: Throwable) {
            Log.i("fail_API_UserData", t.message.toString())
        }

    })

    var searchText by remember { mutableStateOf("") }

    Box(modifier = Modifier.padding()) {
        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Black)
                .clip(RoundedCornerShape(8.dp)),
            placeholder = {
                Text(
                    "Pesquisar por catador ou material...",
                    style = MaterialTheme.typography.body1.copy(color = White)
                )
            },
            leadingIcon = {
                Icon(
                    Icons.Filled.Search,
                    contentDescription = "Ícone de pesquisa",
                    tint = White
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = White,
                cursorColor = White,
                placeholderColor = Color.LightGray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }

    Card(
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 90.dp, bottom = 10.dp, end = 9.dp, start = 9.dp)
            .clip(RoundedCornerShape((2.dp)))
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "Catadores Próximos",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 8.dp)
            )
        }
        LazyColumn(
            modifier = Modifier
                .padding(top = 62.dp)
        ) {
            items(catadores.filter {
                it.nome.contains(
                    searchText,
                    ignoreCase = true
                )
            }) { catador ->
                Card(
                    elevation = 6.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp, horizontal = 4.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {

                        DisplayImageFromUrl(
                            imageUrl = catador.foto,
                            description = "Foto do catador",
                            size = 80.dp,
                            padding = 0.dp
                        )

//                        Image(
//                            painter = painterResource(R.drawable.baseline_person_24),
//                            contentDescription = "${catador.nome}",
//                            modifier = Modifier
//                                .size(80.dp)
//                                .clip(CircleShape),
//                            contentScale = ContentScale.Crop
//                        )

                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(
                                text = catador.nome,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Text(
                                text = catador.cidade,
                                fontSize = 18.sp,
                                color = Color.Gray,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            ) {
                                Button(
                                    modifier = Modifier
                                        .height(35.dp)
                                        .width(140.dp),
                                    onClick = { /* ... */ },
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Color(
                                            8,
                                            113,
                                            19
                                        )
                                    )
                                ) {
                                    Text(text = "Solicite", color = White)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CatadoresProximosPreview() {
    ZeroWasteTestTheme {
        CatadoresProximosContent()
    }
}
