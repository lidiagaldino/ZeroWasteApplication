package br.senai.jandira.sp.zerowastetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import br.senai.jandira.sp.zerowastetest.ui.theme.ZeroWasteTestTheme


class SolicitarColetaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            ZeroWasteTestTheme() {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(108, 162, 76 ),

                    ) {

                    SolicitarColetaContent()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SolicitarColetaContent() {

    val context = LocalContext.current
    val scrollable = rememberScrollState()


    var materiais by remember {
        mutableStateOf(false)
    }
    var checked22 by remember {
        mutableStateOf(false)
    }

    var expandedLocal by remember {
        mutableStateOf(false)
    }

    var expandedMateriais by remember {
        mutableStateOf(false)
    }

    val list2 = listOf("Papel", "Metal", "Plástico", "Vidro", "Orgânico", "Eletrônico")

    val list1 = listOf("Exemplo1", "Exemplo2", "Exemplo3", "Exemplo4")

    var selectedItem by remember {
        mutableStateOf("")
    }

    var textFieldSize by remember {
        mutableStateOf(Size.Zero)
    }

    var selectedLocal by remember {
        mutableStateOf("")
    }

    //val checkedState = remember { mutableStateOf(true) }


    val icon1 = if (expandedLocal) {
        Icons.Filled.KeyboardArrowUp

    } else {
        Icons.Filled.KeyboardArrowDown
    }

    val icon2 = if (expandedMateriais) {
        Icons.Filled.KeyboardArrowUp

    } else {
        Icons.Filled.KeyboardArrowDown
    }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
        //.background(color = Color(120, 160, 87 ))

    ) {

        Text(
            text = stringResource(id = R.string.solicite_coleta),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            fontSize = 19.sp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center,
            color = Color.White
        )

        Text(
            text = stringResource(id = R.string.formulario),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 1.dp, bottom = 1.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center,
            color = Color.Black
        )

        Text(
            text = stringResource(id = R.string.primeiro),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 1.dp),
            fontSize = 17.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            color = Color.Black
        )

        Column(modifier = Modifier.padding(3.dp)) {

            OutlinedTextField(
                value = selectedLocal,
                onValueChange = { selectedLocal = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        textFieldSize = coordinates.size.toSize()
                    },
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color(188, 219, 183),),
//                shape = RoundedCornerShape(16.dp),
                label = { Text(text = "Selecione o local") },

                trailingIcon = {
                    Icon(icon1, "", Modifier.clickable { expandedLocal = !expandedLocal })
                }
            )

            DropdownMenu(
                expanded = expandedLocal,
                onDismissRequest = { expandedLocal = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) { textFieldSize.width.toDp() }),
//                    .scrollable(),

                ) {
                list1.forEach { label ->
                    DropdownMenuItem(onClick = {
                        selectedLocal = label

                        expandedLocal = false

                    }) {

                        Text(text = label)




                    }


                }
            }

        }





        Text(
            text = stringResource(id = R.string.segundo),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 1.dp),
            fontSize = 17.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            color = Color.Black
        )

        Spacer(modifier = Modifier.padding(7.dp))

        Column() {

            TextField(
                value = selectedItem,
                onValueChange = { selectedItem = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        textFieldSize = coordinates.size.toSize()
                    },
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color(188, 219, 183),),
                label = { Text(text = "Selecione os materias") },

                trailingIcon = {
                    Icon(icon2, "", Modifier.clickable { expandedMateriais = !expandedMateriais })
                }
            )

            DropdownMenu(
                expanded = expandedMateriais,
                onDismissRequest = { expandedMateriais = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) { textFieldSize.width.toDp() }),
                ) {
                list2.forEach { label ->
                    DropdownMenuItem(onClick = {
                        selectedItem = label
                        expandedMateriais = true

                    }) {

                        Text(text = label)

                        Spacer(modifier = Modifier.fillMaxWidth())

                        val checkedState = remember { mutableStateOf(false) }
                        Checkbox(
                            checked = checkedState.value,
                            onCheckedChange = { checkedState.value = it },
                        )

                    }

                }

            }

        }



        Spacer(modifier = Modifier.padding(10.dp))


        Button(onClick = { /*TODO*/ }, modifier = Modifier
            .fillMaxWidth()
            .height(53.dp)
            .padding(top = 7.dp, start = 40.dp, end = 40.dp),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(107, 177, 115))
        ) {
            Text(text = "SOLICITAR",
                color = Color.White,
                //fontsize = 18.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }

        Spacer(modifier = Modifier.padding(20.dp))


        Image(
            painter = painterResource(id = R.drawable.cadastrar_end_image),
            contentDescription = stringResource(id = R.string.foto_tela),
            modifier = Modifier
                .align(CenterHorizontally)
                .width(320.dp)
                .height(310.dp)
        )

    }

}


@Preview(showBackground = true)
@Composable
fun SolicitarColetaContentPreview() {
    ZeroWasteTestTheme() {
        SolicitarColetaContent()
    }
}