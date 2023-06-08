package br.senai.jandira.sp.zerowastetest

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.senai.jandira.sp.zerowastetest.api.ApiCalls
import br.senai.jandira.sp.zerowastetest.api.RetrofitApi
import br.senai.jandira.sp.zerowastetest.dataSaving.SessionManager
import br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.modelUser.*
import br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.modelUser.modelCatador.Catador
import br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.modelUser.modelCatador.CatadorFavorito
import br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.modelUser.modelCatador.MateriaisCatador
import br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.modelUser.modelGerador.Gerador
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
        val cleanToken = sessionManager.fetchAuthToken()
        val authToken = "Bearer $cleanToken"


        var contact = listOf(Favoritos(CatadorFavorito(id = 0, id_status_catador = 0, id_usuario = 0, user = UserData(id = 0, biografia = "", email = "", foto = "", telefone = "", senha = "", pessoa_juridica = listOf(PessoaJuridica(id = "0", id_usuario = "0", cnpj = "", nome_fantasia = "")), catador = listOf(
            Catador(id = 0, id_usuario = "0", materiais_catador = listOf(MateriaisCatador()))
        ), endereco_usuario = listOf(UserAddress()), gerador = listOf(Gerador()), pessoa_fisica = listOf(PessoaFisica())
        )), id = 0, id_catador = 0, id_gerador = 0))


        val chatHandler = ChatHandler()
        chatHandler.setSocketChat(cleanToken)
        chatHandler.establishConnectionChat()

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
                                        contact = responseFavoritos.body()!!
                                    }


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
fun ContactListScreen(contacts: List<Favoritos>) {
    var contato = listOf(Contato())
    contacts.map {
        contato += Contato(email = it.catador.user.email, foto = it.catador.user.foto, id = it.catador.id_usuario)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Lista de Contatos") })
        },
        content = {
            ContactList(contacts = contato)
        }
    )
}



@Composable
fun ContactList(contacts: List<Contato>) {
    val context = LocalContext.current
    LazyColumn {
        items(contacts) { contact ->
            if (contact.id > 0){
                ContactItem(contact = contact, context = context)
                Divider() // Opcional: adicione um divisor entre os itens
            }

        }
    }
}

@Composable
fun ContactItem(contact: Contato, context: Context) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable {
                val intent = Intent(context, ChatMessages::class.java)
                Log.i("id", contact.id.toString())
                intent.putExtra("id", contact.id.toString())
                context.startActivity(intent)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        DisplayImageFromUrl(imageUrl = contact.foto, description = "foto", size = 50.dp, padding = 0.dp)
        Column(
            modifier = Modifier.padding(start = 16.dp)
        ) {
            Text(text = contact.email, style = MaterialTheme.typography.h6)
        }
    }
}


