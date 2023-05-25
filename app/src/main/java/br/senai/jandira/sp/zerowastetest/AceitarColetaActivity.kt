package br.senai.jandira.sp.zerowastetest

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import br.senai.jandira.sp.zerowastetest.api.LogisticCalls
import br.senai.jandira.sp.zerowastetest.api.RetrofitApi
import br.senai.jandira.sp.zerowastetest.dataSaving.SessionManager
import br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.Address
import br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.Materials
import br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.UserData
import br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.modelPedido.*
import br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelGeocode.Geometry
import br.senai.jandira.sp.zerowastetest.ui.theme.ZeroWasteTestTheme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.gson.Gson
import io.socket.client.Socket
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.CompletableFuture


class AceitarColetaActivity : ComponentActivity() {

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var currentLocation: Geometry? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        val sessionManager = SessionManager(this)
        val authToken = "Bearer " + sessionManager.fetchAuthToken()
        val cleanToken = sessionManager.fetchAuthToken()

        val socketHandler = SocketHandler()
        socketHandler.setSocket(cleanToken)
        socketHandler.establishConnection()



        val mSocket = socketHandler.getSocket()

        val retrofitApi = RetrofitApi.getLogisticApi()
        val orderApi = retrofitApi.create(LogisticCalls::class.java)


        val order = PedidoReturn(
            pedido = Pedido(
                endereco = Address(
                    apelido = "",
                    bairro = "",
                    cep = "",
                    cidade = "",
                    complemento = "",
                    estado = "",
                    id = 0,
                    latitude = 0.0,
                    logradouro = "",
                    longitude = 0.0,
                    numero = ""
                ),
                id = 0,
                id_gerador = 0,
                id_endereco = 0,
                created_at = "",
                finished_at = "",
                id_catador = 0,
                id_status = 0,
                FilaPedidoCatador = listOf(
                    FilaPedidoCatador(
                        distancia = 0
                    )
                ),
                MateriaisPedido = listOf(
                    MateriaisPedido(
                        material = Materials(
                            nome = ""
                        )
                    )
                ),
                tbl_gerador = TblGerador(
                    user = UserData(
                        pessoa_fisica = listOf(),
                        pessoa_juridica = listOf()
                    )

                )
            )
        )

        orderApi.getOrder(authToken)
            .enqueue(object : Callback<PedidoReturn> {
                override fun onResponse(
                    call: Call<PedidoReturn>,
                    response: Response<PedidoReturn>
                ) {
                    if (response.isSuccessful) {
                        order.pedido = response.body()!!.pedido
                    } else {
                        order.pedido = null
                    }



                    fetchLocation().thenAccept{location ->
                        currentLocation = location

                        setContent {



                            ZeroWasteTestTheme {
                                // A surface container using the 'background' color from the theme
                                Surface(
                                    modifier = Modifier.fillMaxSize(),
                                    color = colorResource(id = R.color.light_green)
                                ) {

                                    currentLocation?.let { location ->
                                        Log.i("location", currentLocation.toString())
                                        AceitarColetaContent(mSocket, order, location, authToken)
                                    }
                                }
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<PedidoReturn>, t: Throwable) {
                    Log.i("fail", t.message.toString())
                }
            })
    }

    fun fetchLocation(): CompletableFuture<Geometry>{
        val completableFuture = CompletableFuture<Geometry>()

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                101
            )
        }

        fusedLocationProviderClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    val latLong = Geometry(
                        lat = location.latitude,
                        lng = location.longitude
                    )
                    completableFuture.complete(latLong)
                } else {
                    // Caso não seja possível obter a localização
                    completableFuture.complete(null)
                }
            }
            .addOnFailureListener { e ->
                // Em caso de erro ao obter a localização
                completableFuture.completeExceptionally(e)
            }

        return completableFuture
    }
}


@Composable
fun AceitarColetaContent(mSocket: Socket, pedido: PedidoReturn, location: Geometry, authToken: String) {

    val retrofitApi = RetrofitApi.getLogisticApi()
    val orderApi = retrofitApi.create(LogisticCalls::class.java)
    val context = LocalContext.current

    var order by remember {
        mutableStateOf(pedido)
    }

    var locked by remember {
        mutableStateOf(order.pedido != null)
    }

    var isAccept by remember {
        mutableStateOf(order.pedido?.id_status == 2)
    }


    Log.i("oi", order.toString())

    mSocket.on("newOrder") { pedido ->
        val json = pedido[0].toString()
        Log.i("json", json)
        val orderPedido = Gson().fromJson(json, PedidoResponse::class.java)
        order = PedidoReturn(
            pedido = Pedido(
                endereco = orderPedido.endereco,
                id = orderPedido.id,
                id_gerador = orderPedido.id_gerador,
                id_endereco = orderPedido.endereco.id,
                created_at = orderPedido.created_at,
                finished_at = "",
                id_catador = orderPedido.id_catador,
                id_status = orderPedido.id_status,
                FilaPedidoCatador = listOf(
                    FilaPedidoCatador(
                        distancia = orderPedido.distancia ?: 0
                    )
                ),
                MateriaisPedido = orderPedido.id_material,
                tbl_gerador = TblGerador(
                    user = UserData(
                        pessoa_fisica = listOf(),
                        pessoa_juridica = listOf()
                    )

                )
            )
        )
        locked = true
        isAccept = false
    }

    mSocket.on("acceptOrder") {
        isAccept = true
    }

    mSocket.on("canceledOrder") {
        locked = false
    }

    val nothing = ""

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(255, 255, 255))
    )
    {

        if (!locked) {
            Box(Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(R.drawable.imagem_fundo),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize()
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                )
                {

                    Image(painter = painterResource(id = R.drawable.back_arrow),
                        contentDescription = "Voltar",
                        modifier = Modifier
                            .size(40.dp)
                            .padding(start = 10.dp, top = 10.dp)
                            .clickable {
                                val backToHome = Intent(context, HomeActivity::class.java)
                                context.startActivity(backToHome)
                            }
                    )

                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
                    {

                        Card(
                            modifier = Modifier
                                .width(320.dp)
                                .height(320.dp)
                                .padding(start = 25.dp, end = 25.dp),
                            shape = RoundedCornerShape(20.dp),
                            backgroundColor = Color(255, 255, 255)
                        )
                        {

                            Image(
                                painter = painterResource(R.drawable.imagem_fundo_card),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.FillBounds
                            )

                            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center)
                            {
                                Text(
                                    modifier = Modifier.padding(),
                                    textAlign = TextAlign.Center,
                                    text = stringResource(id = R.string.empty_pickuprequests),
                                    fontSize = 22.sp,
                                    color = Color.Black
                                )
                                Image(
                                    painter = painterResource(R.drawable.foto_recycle),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(180.dp)
                                        .padding(top = 20.dp),

                                    )


                            }
                        }
                    }
                }

            }
        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
            {

                Card(
                    modifier = Modifier
                        .width(480.dp)
                        .height(680.dp)
                        .padding(start = 25.dp, end = 25.dp),
                    shape = RoundedCornerShape(20.dp),
                    backgroundColor = Color(255, 255, 255)
                )
                {

                    Image(
                        painter = painterResource(R.drawable.image_fundo),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )

                    Column(horizontalAlignment = Alignment.CenterHorizontally)
                    {
                        Text(
                            modifier = Modifier.padding(top = 25.dp),
                            text = stringResource(id = R.string.accept_request),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Text(
                            modifier = Modifier.padding(),
                            textAlign = TextAlign.Center,
                            text = "${
                                if (order.pedido?.tbl_gerador?.user?.pessoa_fisica?.isNotEmpty() == true) order.pedido!!.tbl_gerador?.user?.pessoa_fisica?.get(
                                    0
                                )?.nome else if(order.pedido?.tbl_gerador?.user?.pessoa_juridica?.isNotEmpty() == true) order.pedido?.tbl_gerador?.user?.pessoa_juridica?.get(0)?.nome_fantasia else nothing
                            }",
                            fontSize = 23.sp,
                            color = colorResource(id = R.color.light_green)
                        )

                        Image(
                            painter = painterResource(id = R.drawable.lixeira_reciclagem),
                            contentDescription = "",
                            modifier = Modifier
                                .size(130.dp)
                                .padding(top = 15.dp)
                        )

                        Text(
                            modifier = Modifier.padding(18.dp),
                            textAlign = TextAlign.Center,
                            text = stringResource(id = R.string.distance),
                            fontSize = 21.sp,
                            color = Color.Black
                        )

                        Text(
                            modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                            textAlign = TextAlign.Center,
                            text = "${order.pedido?.FilaPedidoCatador?.get(0)?.distancia} de distância do endereço principal",
                            fontSize = 20.sp,
                            color = colorResource(id = R.color.light_green)
                        )

                        Text(
                            modifier = Modifier.padding(10.dp),
                            textAlign = TextAlign.Center,
                            text = stringResource(id = R.string.localization),
                            fontSize = 21.sp,
                            color = Color.Black
                        )

                        Text(
                            modifier = Modifier,
                            textAlign = TextAlign.Center,
                            text = "${order.pedido?.endereco?.cidade}, ${order.pedido?.endereco?.logradouro} - ${order.pedido?.endereco?.numero}, ${order.pedido?.endereco?.estado}",
                            fontSize = 20.sp,
                            color = colorResource(id = R.color.light_green)
                        )

                        Text(
                            modifier = Modifier.padding(10.dp),
                            textAlign = TextAlign.Center,
                            text = stringResource(id = R.string.pickup_materials),
                            fontSize = 21.sp,
                            color = Color.Black
                        )

                        Text(
                            modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                            textAlign = TextAlign.Center,
                            text = "${
                                order.pedido?.MateriaisPedido?.map {
                                    it.material.nome
                                }
                            }",
                            fontSize = 20.sp,
                            color = colorResource(id = R.color.light_green)
                        )






                        Column(
                            modifier = Modifier
                                .padding(start = 132.dp, top = 20.dp, end = 2.dp, bottom = 8.dp)
                                .width(90.dp)
                                .height(90.dp),
                            horizontalAlignment = Alignment.End
                        ) {

                        }

                        Row {

                            if (!isAccept) {
                                Button(
                                    onClick = {
                                        orderApi.denyOrder(authToken, order.pedido?.id)
                                            .enqueue(object : Callback<Void> {
                                                override fun onResponse(
                                                    call: Call<Void>,
                                                    response: Response<Void>
                                                ) {
                                                    if (response.isSuccessful){
                                                        locked = false
                                                    } else{
                                                        Log.i("oi", response.code().toString())
                                                    }
                                                }

                                                override fun onFailure(
                                                    call: Call<Void>,
                                                    t: Throwable
                                                ) {
                                                    Log.i("fail", t.message.toString())
                                                }

                                            })
                                    }, modifier = Modifier
                                        .padding(top = 15.dp)
                                        .width(120.dp)
                                        .height(45.dp), shape = RoundedCornerShape(20.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Color(
                                            217,
                                            32,
                                            32
                                        )
                                    )
                                )
                                {
                                    Text(
                                        text = stringResource(id = R.string.deny),
                                        color = Color.White,
                                        fontWeight = FontWeight.SemiBold, fontSize = 17.sp
                                    )
                                }

                                Spacer(modifier = Modifier.width(7.dp))

                                Button(
                                    onClick = {
                                        orderApi.acceptOrder(
                                            authToken,
                                            order.pedido?.id
                                        )
                                            .enqueue(object : Callback<PedidoResponse> {
                                                override fun onResponse(
                                                    call: Call<PedidoResponse>,
                                                    response: Response<PedidoResponse>
                                                ) {
                                                    Log.i("aceote", response.body()!!.toString())
                                                    order.pedido?.id_status =
                                                        response.body()!!.id_status
                                                    order.pedido?.id_catador =
                                                        response.body()!!.id_catador
                                                }

                                                override fun onFailure(
                                                    call: Call<PedidoResponse>,
                                                    t: Throwable
                                                ) {
                                                    Log.i("fail", t.message.toString())
                                                }

                                            })
                                    }, modifier = Modifier
                                        .padding(top = 15.dp)
                                        .width(200.dp)
                                        .height(45.dp), shape = RoundedCornerShape(20.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = colorResource(id = R.color.light_green)
                                    )
                                )
                                {
                                    Text(
                                        text = stringResource(id = R.string.accept),
                                        color = Color.White,
                                        fontWeight = FontWeight.SemiBold, fontSize = 17.sp
                                    )
                                }
                            } else {
                                Log.i("id", order.pedido?.id.toString())
                                Button(

                                    onClick = {
                                        Log.i("loc", location.toString())
                                        orderApi.finishOrder(
                                            authToken,
                                            order.pedido?.id,
                                            location
                                        ).enqueue(object : Callback<FinishOrder> {
                                            override fun onResponse(
                                                call: Call<FinishOrder>,
                                                response: Response<FinishOrder>
                                            ) {
                                                if (response.isSuccessful){
                                                    Log.i("teste", response.body()!!.toString())
                                                    locked = false
                                                } else{
                                                    Log.i("teste", response.code().toString())
                                                }

                                            }
                                            override fun onFailure(
                                                call: Call<FinishOrder>,
                                                t: Throwable
                                            ) {
                                                Log.i("fail", t.message.toString())
                                            }

                                        })
                                    }, modifier = Modifier
                                        .padding(top = 15.dp)
                                        .width(200.dp)
                                        .height(45.dp), shape = RoundedCornerShape(20.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = colorResource(id = R.color.light_green)
                                    )
                                )
                                {
                                    Text(
                                        text = stringResource(id = R.string.finish_run),
                                        color = Color.White,
                                        fontWeight = FontWeight.SemiBold, fontSize = 17.sp
                                    )
                                }

                            }

                        }

                    }

                }

            }

        }

    }

}
