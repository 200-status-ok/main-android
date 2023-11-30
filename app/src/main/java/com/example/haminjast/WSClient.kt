package com.example.haminjast

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import java.util.concurrent.TimeUnit

class WSClient : WebSocketListener() {

    companion object {
        const val TOKEN =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHBpcmVkIjoiMjAyMy0xMi0wNVQyMzoxMjoxNi4zNjI0NTg1NjNaIiwiaWQiOiJjOTE4MmRhNS0yNDQwLTQ5NTYtOGU3OC0yZmM5ODMxMTExZTEiLCJpc3N1ZWRBdCI6IjIwMjMtMTEtMjhUMjM6MTI6MTYuMzYyNDU4MTE2WiIsInJvbGUiOiJVc2VyIiwidXNlcklkIjoxLCJ1c2VybmFtZSI6IjA5MTk2MTkwNTMzIn0.KchbpVVHa8Zn5-26r9RmIBbuixEacVxZ4L3BsUlhfXI"
    }

    fun run(token: String = TOKEN): WebSocket {
        val client: OkHttpClient = OkHttpClient.Builder()
            .readTimeout(0, TimeUnit.MILLISECONDS)
            .build()
        val request: Request = Request.Builder()
            .url("ws://localhost:8080/api/v1/chat/open-ws?token=$token") //TODO token
            .build()

        val ws = client.newWebSocket(request, this)

        // Trigger shutdown of the dispatcher's executor so this process can exit cleanly.
        client.dispatcher.executorService.shutdown()

        return ws
    }

    override fun onOpen(webSocket: WebSocket, response: Response) {
        webSocket.send("Hello...")
        webSocket.send("...World!")
        webSocket.close(1000, "Goodbye, World!")
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        println("MESSAGE: $text")
    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        println("MESSAGE: " + bytes.hex())
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        webSocket.close(1000, null)
        println("CLOSE: $code $reason")
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        t.printStackTrace()
    }
}