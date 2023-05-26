package br.senai.jandira.sp.zerowastetest

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalComposeUiApi::class, ExperimentalAnimationApi::class)
@Composable
fun CustomDialogOthersProfile(
    onDismiss:()->Unit,
    onConfirm: (value: Float) -> Unit
) {
    Dialog(
        onDismissRequest = {
            onDismiss()
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card(
            elevation = 5.dp,
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .fillMaxHeight(0.5f)
                .border(2.dp, color = Color.White, shape = RoundedCornerShape(15.dp))
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                var rating by remember { mutableStateOf(1) }
                val emoji by derivedStateOf {
                    when (rating) {
                        1 -> "\uD83D\uDE2D️"
                        2 -> "\uD83D\uDE22️"
                        3 -> "\uD83D\uDE1F️"
                        4 -> "\uD83D\uDE42️"
                        else -> "\uD83D\uDE01️"
                    }
                }

                AnimatedContent(targetState = emoji) {
                    Text(text = it, fontSize = 160.sp)
                }

                Spacer(modifier = Modifier.height(16.dp))

                RatingBar(
                    value = rating.toFloat(),
                    onValueChange = {
                        if (it in 1f..5f)
                            rating = it.toInt()
                    },
                    onRatingChanged = {},
                    config = RatingBarConfig()
                        .size(48.dp)
                )

                Row(){
                    Button(onClick = { onDismiss() },
                        modifier = Modifier
                            .fillMaxWidth(0.4f)
                            .padding(start = 10.dp, end = 5.dp),
                        colors = ButtonDefaults.buttonColors(
                            Color.Red
                        )) {
                        Text(text = "Cancelar")
                    }

                    Button(onClick = {
                        onConfirm(rating.toFloat())
                    },
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .padding(start = 10.dp, end = 5.dp),
                        colors = ButtonDefaults.buttonColors(
                            colorResource(id = R.color.dark_green)
                        )) {
                        Text(text = "Avaliar")
                    }
                }


            }
        }
    }
}