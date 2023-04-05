package br.senai.jandira.sp.zerowastetest

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.jandira.sp.zerowastetest.ui.theme.ZeroWasteTestTheme

class EditProfileCatadorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZeroWasteTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ProfileCatadorContent()
                }
            }
        }
    }
}

@Composable
fun ProfileCatadorContent() {

    val context = LocalContext.current
    val scrollState = rememberScrollState()


    var nameState by remember {
        mutableStateOf("")
    }


//    var nameEdit by remember {
//        mutableStateOf(true)
//    }


    var emailState by remember {
        mutableStateOf("")
    }
    var emailEdit by remember {
        mutableStateOf(false)
    }

    var materialsState by remember {
        mutableStateOf("")
    }
    var materialsEdit by remember {
        mutableStateOf(false)
    }

    var telephoneState by remember {
        mutableStateOf("")
    }
    var telephoneEdit by remember {
        mutableStateOf(false)
    }

    var cepState by remember {
        mutableStateOf("")
    }

    var biographyState by remember {
        mutableStateOf("")
    }




    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = br.senai.jandira.sp.zerowastetest.R.drawable.back_arrow),
                contentDescription = "Voltar para página inicial",
                modifier = Modifier
                    .size(26.dp)
                    .clickable {
                        val intent = Intent(context, HomeActivity::class.java)
                        context.startActivity(intent)
                    }
            )
            Card(
                modifier = Modifier.padding(start = 64.dp, end = 80.dp), border = BorderStroke(
                    2.dp, color = colorResource(
                        id = br.senai.jandira.sp.zerowastetest.R.color.lighter_gray
                    )
                ), shape = RoundedCornerShape(0.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = br.senai.jandira.sp.zerowastetest.R.drawable.avatar_standard_icon),
                        contentDescription = "",
                        modifier = Modifier
                            .size(30.dp)
                            .padding(4.dp)
                            .clip(CircleShape)
                    )
                    Text(
                        text = stringResource(id = br.senai.jandira.sp.zerowastetest.R.string.profile_text),
                        modifier = Modifier.padding(start = 4.dp, end = 6.dp),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = br.senai.jandira.sp.zerowastetest.R.string.my_account),
            modifier = Modifier.padding(start = 16.dp),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            backgroundColor = colorResource(id = br.senai.jandira.sp.zerowastetest.R.color.dark_green),
            shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = br.senai.jandira.sp.zerowastetest.R.drawable.avatar_standard_icon),
                    contentDescription = "Foto de perfil",
                    modifier = Modifier
                        .size(130.dp)
                        .clip(
                            CircleShape
                        )
                )
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.padding(bottom = 12.dp),
                    shape = (RoundedCornerShape(0.dp)),
                    colors = ButtonDefaults.buttonColors(colorResource(id = br.senai.jandira.sp.zerowastetest.R.color.fading_gray))
                ) {
                    Text(
                        text = stringResource(id = br.senai.jandira.sp.zerowastetest.R.string.edit_profile_picture),
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                Text(
                    text = "Gerador de Resíduos",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
                Divider(
                    modifier = Modifier.padding(start = 10.dp, end = 26.dp, bottom = 8.dp),
                    color = Color.White,
                    thickness = 0.5f.dp
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, top = 8.dp, end = 40.dp)
                ) {
                    Text(
                        text = stringResource(id = br.senai.jandira.sp.zerowastetest.R.string.username_text),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.fillMaxWidth(0.9f))
                    Image(
                        painter = painterResource(id = br.senai.jandira.sp.zerowastetest.R.drawable.edit_icon),
                        contentDescription = "",
                        alignment = Alignment.CenterEnd
                    )
                }

                TextField(
                    value = nameState,
                    onValueChange = { nameState = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 32.dp)
                        .background(
                            color = colorResource(id = br.senai.jandira.sp.zerowastetest.R.color.dark_green),
                            shape = RoundedCornerShape(0.dp)
                        ),
                    colors = TextFieldDefaults.textFieldColors(
                        Color.White,
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = colorResource(
                            id = br.senai.jandira.sp.zerowastetest.R.color.light_green
                        )
                    )
                )
//                TextField(
//                    text = "Nome do usuário",
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(start = 24.dp, top = 8.dp),
//                    textAlign = TextAlign.Start,
//                    color = Color.White
//                )

                Spacer(modifier = Modifier.height(8.dp))

                Divider(
                    modifier = Modifier.padding(start = 10.dp, end = 26.dp, bottom = 8.dp),
                    color = Color.White,
                    thickness = 0.5f.dp
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, top = 8.dp, end = 40.dp)
                ) {
                    Text(
                        text = stringResource(id = br.senai.jandira.sp.zerowastetest.R.string.user_email_text),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.fillMaxWidth(0.9f))
                    Image(
                        painter = painterResource(id = br.senai.jandira.sp.zerowastetest.R.drawable.edit_icon),
                        contentDescription = "",
                        modifier = Modifier.clickable { /*TODO*/ },
                        alignment = Alignment.CenterEnd
                    )
                }
                Text(
                    text = "Email do usuário",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, top = 8.dp),
                    textAlign = TextAlign.Start,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(8.dp))

                Divider(
                    modifier = Modifier.padding(start = 10.dp, end = 26.dp, bottom = 8.dp),
                    color = Color.White,
                    thickness = 0.5f.dp
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, top = 8.dp, end = 40.dp)
                ) {
                    Text(
                        text = stringResource(id = br.senai.jandira.sp.zerowastetest.R.string.materials_recycle_text),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.fillMaxWidth(0.8f))
                    Image(
                        painter = painterResource(id = br.senai.jandira.sp.zerowastetest.R.drawable.edit_icon),
                        contentDescription = "",
                        modifier = Modifier.clickable { /*TODO*/ },
                        alignment = Alignment.CenterEnd
                    )
                }
                Text(
                    text = "Lixo que recicla",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, top = 8.dp),
                    textAlign = TextAlign.Start,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(8.dp))

                Divider(
                    modifier = Modifier.padding(start = 10.dp, end = 26.dp, bottom = 8.dp),
                    color = Color.White,
                    thickness = 0.5f.dp
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, top = 8.dp, end = 40.dp)
                ) {
                    Text(
                        text = stringResource(id = br.senai.jandira.sp.zerowastetest.R.string.user_telephone_text),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.fillMaxWidth(0.9f))
                    Image(
                        painter = painterResource(id = br.senai.jandira.sp.zerowastetest.R.drawable.edit_icon),
                        contentDescription = "",
                        modifier = Modifier.clickable { /*TODO*/ },
                        alignment = Alignment.CenterEnd
                    )
                }
                Text(
                    text = "Telefone do usuário",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, top = 8.dp),
                    textAlign = TextAlign.Start,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(8.dp))

                Divider(
                    modifier = Modifier.padding(start = 10.dp, end = 26.dp, bottom = 8.dp),
                    color = Color.White,
                    thickness = 0.5f.dp
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, top = 8.dp, end = 40.dp)
                ) {
                    Text(
                        text = stringResource(id = br.senai.jandira.sp.zerowastetest.R.string.user_cep_text),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.fillMaxWidth(0.9f))
                    Image(
                        painter = painterResource(id = br.senai.jandira.sp.zerowastetest.R.drawable.edit_icon),
                        contentDescription = "",
                        modifier = Modifier.clickable { /*TODO*/ },
                        alignment = Alignment.CenterEnd
                    )
                }
                Text(
                    text = "Cidade do usuário",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, top = 8.dp),
                    textAlign = TextAlign.Start,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(8.dp))

                Divider(
                    modifier = Modifier.padding(start = 10.dp, end = 26.dp, bottom = 8.dp),
                    color = Color.White,
                    thickness = 0.5f.dp
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, top = 8.dp, end = 40.dp)
                ) {
                    Text(
                        text = stringResource(id = br.senai.jandira.sp.zerowastetest.R.string.user_biography),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.fillMaxWidth(0.9f))
                    Image(
                        painter = painterResource(id = br.senai.jandira.sp.zerowastetest.R.drawable.edit_icon),
                        contentDescription = "",
                        modifier = Modifier.clickable { /*TODO*/ },
                        alignment = Alignment.CenterEnd
                    )
                }
                Text(
                    text = stringResource(id = br.senai.jandira.sp.zerowastetest.R.string.lorem_ipsum),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, top = 26.dp),
                    textAlign = TextAlign.Start,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ContentPreview() {
    ZeroWasteTestTheme {
        ProfileCatadorContent()
    }
}