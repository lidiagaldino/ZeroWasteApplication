package br.senai.jandira.sp.zerowastetest

import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

class SocketHandler {
    lateinit var mSocket: Socket

    @Synchronized
    fun setSocket() {
        try {
            val options = IO.Options()
            options.auth = mapOf("token" to "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NSwidXNlcl90eXBlIjoiQ0FUQURPUiIsImlkX3VzdWFyaW8iOjUsImlkX21vZG8iOjMsImlhdCI6MTY4NDQzMzQwOSwiZXhwIjoxNjg0NTE5ODA5fQ.j0zkQ1FgyrxfRNl9cSmMOJRWOeM8STVfytmlIzK4UXw")
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