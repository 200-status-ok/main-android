package com.example.haminjast.data.datastore

import android.content.Context
import com.example.haminjast.User

class LoginDataStore(private val context: Context) {
    fun saveTokenF(token: String){
        if(context.filesDir.listFiles()?.any { it.name == "token" } == true){
            context.deleteFile("token")
        }
        val file = context.openFileOutput("token", Context.MODE_PRIVATE)
        file.write(token.toByteArray())
        file.close()
    }

    fun readTokenF(): String{
        return if(context.filesDir.listFiles()?.any { it.name == "token" } == true){
            val file = context.openFileInput("token")
            val token = file.readBytes().toString(Charsets.UTF_8)
            file.close()
            User.token= token
            token
        }else{
            ""
        }
    }

    fun saveIdF(id: String){
        if(context.filesDir.listFiles()?.any { it.name == "id" } == true){
            context.deleteFile("id")
        }
        val file = context.openFileOutput("id", Context.MODE_PRIVATE)
        file.write(id.toByteArray())
        file.close()
    }

    fun readIdF(): String?{
        return if(context.filesDir.listFiles()?.any { it.name == "id" } == true){
            val file = context.openFileInput("id")
            val id = file.readBytes().toString(Charsets.UTF_8)
            file.close()
            User.id= id.toLong()
            id
        }else{
            null
        }
    }

    fun saveWallet(balance : Int){
        if(context.filesDir.listFiles()?.any { it.name == "balance" } == true){
            context.deleteFile("balance")
        }
        val file = context.openFileOutput("balance", Context.MODE_PRIVATE)
        file.write(balance.toString().toByteArray())
        file.close()
    }

    fun readWallet(): Int{
        return if(context.filesDir.listFiles()?.any { it.name == "balance" } == true){
            val file = context.openFileInput("balance")
            val balance = file.readBytes().toString(Charsets.UTF_8).toInt()
            file.close()
            balance
        }else{
            0
        }
    }
}