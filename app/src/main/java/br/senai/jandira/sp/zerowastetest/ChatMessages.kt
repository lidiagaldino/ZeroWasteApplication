package br.senai.jandira.sp.zerowastetest

import android.os.Bundle
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

import br.senai.jandira.sp.zerowastetest.ui.theme.ZeroWasteTestTheme

class ChatMessages : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val messages = listOf(
            Messages("Olá!"),
            Messages("Como você está?"),
            Messages("Tudo bem por aí?")
        )

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
    Surface(
        shape = RoundedCornerShape(4.dp),
        color = MaterialTheme.colors.primary,
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

data class Messages(val text: String)