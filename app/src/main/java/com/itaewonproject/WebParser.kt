package com.itaewonproject

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.concurrent.ExecutionException

object WebParser : AsyncTask<String, Void, String>() {

    private var receiveMSG="" as String
    private val key="AIzaSyD_d8P1HxLWAdC0AEJvWKkujn8yNQmqbJE" as String
    var placeID=""
    fun getInfo(placeID:String):String{
        this.placeID=placeID
        var str=""
        try{
            str = this.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR).get()

        }catch(e:InterruptedException){
            e.printStackTrace()
        }catch(e: ExecutionException){
            e.printStackTrace()
        }
        return str
    }


    override fun doInBackground(vararg p0: String?): String {
        try{
            var url = URL("https://maps.googleapis.com/maps/api/place/details/json?placeid=${placeID}&fields=name,rating,formatted_phone_number,opening_hours&key=${key}") as URL
            Log.i("url","${url.toString()}")
            var conn = url.openConnection() as HttpURLConnection
            if(conn.responseCode == HttpURLConnection.HTTP_OK){
                var tmp = InputStreamReader(conn.inputStream,"UTF-8")
                var bufferedReader = BufferedReader(tmp)
                var stringBuffer=StringBuffer()

                for(string in bufferedReader.readLines()){
                    stringBuffer.append(string)
                }
                receiveMSG = stringBuffer.toString()
                Log.i("receivedMessage","${receiveMSG}")
            }else
            {
                Log.i("error","${conn.responseCode} 에러")
            }

        }catch(e : MalformedURLException){
            e.printStackTrace()
        }catch(e: IOException){
            e.printStackTrace()
        }

        return receiveMSG
    }
}