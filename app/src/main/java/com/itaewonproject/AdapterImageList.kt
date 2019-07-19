package com.itaewonproject

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import android.net.Uri
import android.os.AsyncTask
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.concurrent.ExecutionException

class AdapterImageList(val context: Context, var images:ArrayList<Bitmap>) : RecyclerView.Adapter<AdapterImageList.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_image,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    fun setImage(images:ArrayList<Bitmap>){
        this.images=images
        notifyDataSetChanged()
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var img:ImageView
        init{
            img = itemView.findViewById(R.id.imageView) as ImageView

        }
        fun bind(pos:Int){
            //img.setImageBitmap(APIs.BitmapFromURL(url,300,300))
            img.setImageBitmap(images[pos])
        }
    }



}