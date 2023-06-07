package br.senai.jandira.sp.zerowastetest

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.senai.jandira.sp.zerowastetest.api.ApiCalls
import br.senai.jandira.sp.zerowastetest.api.RetrofitApi
import br.senai.jandira.sp.zerowastetest.dataSaving.SessionManager
import br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.modelUser.Contato
import br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.modelUser.Favoritos
import br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.modelUser.UserData
import br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.modelUser.modelCatador.CatadorFavorito
import br.senai.jandira.sp.zerowastetest.ui.theme.ZeroWasteTestTheme
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Chat : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val api = RetrofitApi.getMainApi()
        val mainApi = api.create(ApiCalls::class.java)
        val sessionManager = SessionManager(this)
        val authToken = "Bearer " + sessionManager.fetchAuthToken()

        var contact = listOf(Contato())

        mainApi.getUserData(authToken).enqueue(object : Callback<UserData> {
            override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                if (response.isSuccessful){
                    if (response.body()!!.catador?.isEmpty() == true) {
                        mainApi.getFavoritos(authToken, response.body()!!.gerador?.get(0)!!.id)
                            .enqueue(object : Callback<List<Favoritos>> {
                                override fun onResponse(
                                    call: Call<List<Favoritos>>,
                                    responseFavoritos: Response<List<Favoritos>>
                                ) {
                                    if (responseFavoritos.isSuccessful){
                                        responseFavoritos.body()!!.map {
                                            //Log.i("fav", it.toString())
                                            contact.plus(Contato(email = it.catador.user.email, foto = it.catador.user.foto))
                                        }
                                    }

                                    Log.i("cotnact", contact.toString())

                                    setContent {
                                        ZeroWasteTestTheme {
                                            // A surface container using the 'background' color from the theme
                                            Surface(
                                                modifier = Modifier.fillMaxSize(),
                                                color = MaterialTheme.colors.background
                                            ) {
                                                ContactListScreen(contact)
                                            }
                                        }
                                    }
                                }

                                override fun onFailure(call: Call<List<Favoritos>>, t: Throwable) {
                                    Log.i("fail", t.message.toString())
                                }

                            })
                    } else {

                    }
                }
            }

            override fun onFailure(call: Call<UserData>, t: Throwable) {
                Log.i("fail", t.message.toString())
            }

        })


    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ContactListScreen(contacts: List<Contato>) {

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Lista de Contatos") })
        },
        content = {
            ContactList(contacts = contacts)
        }
    )
}

@Composable
fun ContactList(contacts: List<Contato>) {
    LazyColumn {
        items(contacts) { contact ->
            ContactItem(contact = contact)
            Divider() // Opcional: adicione um divisor entre os itens
        }
    }
}

@Composable
fun ContactItem(contact: Contato) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        DisplayImageFromUrl(imageUrl = contact.foto, description = "foto", size = 10.dp, padding = 0.dp)
        Column(
            modifier = Modifier.padding(start = 16.dp)
        ) {
            Text(text = contact.email, style = MaterialTheme.typography.h6)
        }
    }
}


