package br.senai.jandira.sp.zerowastetest.view

import android.content.Context
import android.content.Intent
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import br.senai.jandira.sp.zerowastetest.HomeActivity
import br.senai.jandira.sp.zerowastetest.MyProfileActivity
import br.senai.jandira.sp.zerowastetest.models.modelSlider.fotoslist
import com.google.accompanist.pager.*
import br.senai.jandira.sp.zerowastetest.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue


@ExperimentalPagerApi
@Composable
fun ViewPagerSlider(context: Context) {

    val pagerState = rememberPagerState(
        pageCount = fotoslist.size,
        initialPage = 0
    )

    LaunchedEffect(Unit) {
        while (true) {
            yield()
            delay(5000)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount),
                animationSpec = tween(600)
            )
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp, start = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.back_arrow),
                contentDescription = "Voltar à página inicial",
                modifier = Modifier
                    .clickable {
                        val intent = Intent(context, HomeActivity::class.java)
                        context.startActivity(intent)
                    }
                    .size(30.dp)
            )
            Text(
                text = "O que Reciclar?",
                modifier = Modifier.fillMaxWidth(),
                color = colorResource(id = R.color.dark_green),
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
        }


        //Spacer(modifier = Modifier.height(5.dp))
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()

        ) { page ->
            Card(modifier = Modifier
                .graphicsLayer {
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                    lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale

                    }
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )

                }
                .fillMaxWidth(),
//                .padding(top = 10.dp),
                shape = RoundedCornerShape(16.dp)
            ) {

                val newFotos = fotoslist[page]
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray)
                        .align(Alignment.Center)

                ) {
                    Image(
                        painter = painterResource(
                            id = newFotos.imgUri

                        ),
                        contentDescription = "Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(10.dp)
                    ) {

                    }

                }

            }

        }

//        Horizontal dot indicator
        HorizontalPagerIndicator(
            pagerState = pagerState, modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        )

    }

}