package com.itaewonproject

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.concurrent.ExecutionException

class ImageParser : AsyncTask<String, Void, ArrayList<Bitmap>>() {
    var x=300
    var y=300
    var urls = ArrayList<String>()
    private lateinit var listener:OnImageParsingDoneListener
    fun getImageAsync(urls:ArrayList<String>,x:Int,y:Int){
        this.urls=urls
        var ret = ArrayList<Bitmap>()
        try{
            ret = this.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR).get()
        }catch(e:InterruptedException){
            e.printStackTrace()
        }catch(e: ExecutionException){
            e.printStackTrace()
        }
        listener.OnImageParsingDone(ret)
    }

    interface OnImageParsingDoneListener{
        fun OnImageParsingDone(images:ArrayList<Bitmap>)
    }

    fun setOnImageParsingDoneListener(listener:OnImageParsingDoneListener){
        this.listener=listener
    }



    override fun doInBackground(vararg p0: String?): ArrayList<Bitmap>{
        var ret = ArrayList<Bitmap>()
        try{
            for(i in urls){
                var conn = URL(i).openConnection() as HttpURLConnection
                conn.doInput=true
                conn.connect()
                val inputStream = conn.inputStream
                ret.add(Bitmap.createScaledBitmap(BitmapFactory.decodeStream(inputStream),x,y,false))
            }
        }catch(e : MalformedURLException){
            e.printStackTrace()
        }catch(e: IOException){
            e.printStackTrace()
        }

        return ret
    }
}