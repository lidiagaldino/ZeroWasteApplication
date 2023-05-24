package br.senai.jandira.sp.zerowastetest

import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

class SocketHandler {
    lateinit var mSocket: Socket

    @Synchronized
    fun setSocket(authToken: String) {
        try {
            val options = IO.Options()
            options.auth = mapOf("token" to authToken)
            mSocket = IO.socket("https://zero-waste-logistic.azurewebsites.net", options)
        } catch (e: URISyntaxException) {

        }
    }

    @Synchronized
    fun getSocket(): Socket {
        return mSocket
    }

    @Synchronized
    fun establishConnection() {
        mSocket.connect()
    }

    @Synchronized
    fun closeConnection() {
        mSocket.disconnect()
    }
}