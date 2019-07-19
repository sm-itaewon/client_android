package com.itaewonproject

import android.content.Context
import android.content.Intent
import android.media.Image
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterArticleList(val context: Context, var output:ArrayList<OutputArticle>) : RecyclerView.Adapter<AdapterArticleList.ViewHolder>() {

    private lateinit var listener:onItemClickListener

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(output[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_article,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return output.size
    }

    fun add(oll:OutputArticle){
        output.add(oll)
        notifyDataSetChanged()
    }

    interface onItemClickListener{
        fun onItemClick(v: View, position:Int)
    }

    fun setOnItemClickClickListener(listener:onItemClickListener){
        this.listener=listener
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var summary: TextView
        private var img:ImageView
        private var buttonRef:ImageButton
        private var articleId=""
        init{
            summary = itemView.findViewById(R.id.text_summary) as TextView
            img = itemView.findViewById(R.id.image_arcticle) as ImageView
            buttonRef=itemView.findViewById(R.id.imageButton_ref) as ImageButton

            buttonRef.setOnClickListener(View.OnClickListener {
                val pos = adapterPosition
                if(pos!=RecyclerView.NO_POSITION){
                    if(listener!=null){
                        listener.onItemClick(it,pos)
                    }
                }
            })
        }
        fun bind(output:OutputArticle){
            articleId=output.article_id
            img.setImageBitmap(output.getImage())
            summary.text=output.summary
            buttonRef.setImageBitmap(output.getRefIcon())
            /*buttonRef.setOnClickListener(View.OnClickListener {
                var intent = Intent(Intent.ACTION_VIEW, Uri.parse(output.link))
                (itemView.parent as Context).startActivity(intent)
            })*/
        }
    }

}