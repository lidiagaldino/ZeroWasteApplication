package br.senai.jandira.sp.zerowastetest

import android.net.DnsResolver.Callback
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.jandira.sp.zerowastetest.api.ApiCalls
import br.senai.jandira.sp.zerowastetest.api.ChatCalls
import br.senai.jandira.sp.zerowastetest.api.RetrofitApi
import br.senai.jandira.sp.zerowastetest.dataSaving.SessionManager
import br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.modelUser.UserData

import br.senai.jandira.sp.zerowastetest.ui.theme.ZeroWasteTestTheme
import retrofit2.Call
import retrofit2.Response

class ChatMessages : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val api = RetrofitApi.getMainApi()
        val mainApi = api.create(ApiCalls::class.java)
        val apiChat = RetrofitApi.getChatApi()
        val chatApi = apiChat.create(ChatCalls::class.java)
        val sessionManager = SessionManager(this)
        val authToken = "Bearer " + sessionManager.fetchAuthToken()
        val messages = listOf(
            Messages("Olá!", true),
            Messages("Como você está?", false),
            Messages("Tudo bem por aí?", false)
        )

        mainApi.getUserData(authToken).enqueue(object : retrofit2.Callback<UserData> {
            override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                if (response.isSuccessful){

                }
            }

            override fun onFailure(call: Call<UserData>, t: Throwable) {
                Log.i("fail", t.message.toString())
            }

        })

        setContent {
            ZeroWasteTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MessageScreen(messages)
                }
            }
        }
    }
}



@Composable
fun MessageScreen(messages: List<Messages>) {
    Surface(color = Color.White) {
        Column(modifier = Modifier.padding(16.dp)) {
            MessageList(messages = messages)
            Spacer(modifier = Modifier.height(16.dp))
            MessageInput(onMessageSent = { message ->
                // Aqui você pode lidar com a lógica para enviar a mensagem
                // Por exemplo, adicionar a mensagem à lista de mensagens
            })
        }
    }
}


@Composable
fun MessageList(messages: List<Messages>) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(messages){ message ->
            MessageItem(message)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun MessageItem(message: Messages) {
    val backgroundColor = if (message.isSentByMe) {
        MaterialTheme.colors.primary
    } else {
        Color.LightGray
    }

    val messageAlignment = if (message.isSentByMe) {
        Alignment.CenterEnd
    } else {
        Alignment.CenterStart
    }
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = messageAlignment
    ) {
        Surface(
            shape = RoundedCornerShape(4.dp),
            color = backgroundColor,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = message.text,
                style = MaterialTheme.typography.body1,
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun MessageInput(onMessageSent: (String) -> Unit) {
    var textState by remember { mutableStateOf(TextFieldValue()) }

    Row(verticalAlignment = Alignment.CenterVertically) {
        TextField(
            value = textState,
            onValueChange = { textState = it },
            modifier = Modifier.weight(1f)
        )
        Button(
            onClick = {
                val message = textState.text
                if (message.isNotEmpty()) {
                    onMessageSent(message)
                    textState = TextFieldValue()
                }
            },
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Text(text = "Enviar")
        }
    }
}

data class Messages(val text: String, val isSentByMe: Boolean)