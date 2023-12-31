package com.example.haminjast

import android.util.Log
import com.example.haminjast.User.token
import com.example.haminjast.data.model.MessageReceivedUpdate
import com.example.haminjast.data.repository.ChatRepository
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import java.util.concurrent.TimeUnit

class WSClient(private val chatRepository: ChatRepository) : WebSocketListener() {

    fun run(): WebSocket {
        val client: OkHttpClient = OkHttpClient.Builder()
            .readTimeout(0, TimeUnit.MILLISECONDS)
            .build()
        val request: Request = Request.Builder()
            .url("wss://main-app.liara.run/api/v1/chat/open-ws?token=$token") //TODO token
            .build()

        val ws = client.newWebSocket(request, this)

        // Trigger shutdown of the dispatcher's executor so this process can exit cleanly.
        client.dispatcher.executorService.shutdown()

        return ws
    }

    override fun onOpen(webSocket: WebSocket, response: Response) {
        Log.d("modar", "ws open")
//        webSocket.send("Hello...")
//        webSocket.send("...World!")
//        webSocket.close(1000, "Goodbye, World!")
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        Log.d("modar", "ws ${Thread.currentThread().name} onMessage1 $text");

        CoroutineScope(Dispatchers.IO).launch {// TODO handle scope
            val receivedMessage = Gson().fromJson(text, MessageReceivedUpdate::class.java)
            chatRepository.onMessageReceived(receivedMessage)
        }

        println("MESSAGE: $text")
    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        Log.d("modar", "ws onMessage2");
        println("MESSAGE: " + bytes.hex())
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        Log.d("modar", "ws onClosing");
        webSocket.close(1000, null)
        println("CLOSE: $code $reason")
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        Log.d("modar", "ws onFailure ${t.message}");
        t.printStackTrace()
    }
}