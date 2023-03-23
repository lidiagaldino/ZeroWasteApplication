package br.senai.jandira.sp.zerowastetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.jandira.sp.zerowastetest.ui.theme.ZeroWasteTestTheme

class MyProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZeroWasteTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ProfileContent()
                }
            }
        }
    }
}

@Composable
fun ProfileContent() {

    val context = LocalContext.current
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = R.drawable.back_arrow),
                contentDescription = "Voltar para página inicial",
                modifier = Modifier
                    .size(26.dp)
                    .clickable { /*TODO*/ }
            )
            Card(
                modifier = Modifier.padding(start = 64.dp, end = 80.dp), border = BorderStroke(
                    2.dp, color = colorResource(
                        id = R.color.lighter_gray
                    )
                ), shape = RoundedCornerShape(0.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.avatar_standard_icon),
                        contentDescription = "",
                        modifier = Modifier
                            .size(30.dp)
                            .padding(4.dp)
                            .clip(CircleShape)
                    )
                    Text(
                        text = stringResource(id = R.string.profile_text),
                        modifier = Modifier.padding(start = 4.dp, end = 6.dp),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.my_account),
            modifier = Modifier.padding(start = 16.dp),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            backgroundColor = colorResource(id = R.color.dark_green),
            shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.avatar_standard_icon),
                    contentDescription = "Foto de perfil",
                    modifier = Modifier
                        .size(130.dp)
                        .clip(
                            CircleShape
                        )
                )
                Button(
                    onClick = { /*TODO*/ },
                    shape = (RoundedCornerShape(0.dp)),
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.fading_gray))
                ) {
                    Text(
                        text = stringResource(id = R.string.edit_profile_picture),
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                Image(painter = painterResource(id = R.drawable.content_divisor_white), contentDescription = "", modifier = Modifier.width(1440.dp).padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview2() {
    ZeroWasteTestTheme {
        ProfileContent()
    }
}