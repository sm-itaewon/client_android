package com.itaewonproject.RecyclerviewAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.itaewonproject.ServerResult.Article
import com.itaewonproject.R
import com.itaewonproject.RatioTransformation
import com.squareup.picasso.Picasso

class AdapterArticleList(val context: Context, var output:ArrayList<Article>) : RecyclerView.Adapter<AdapterArticleList.ViewHolder>() {

    private lateinit var listener: onItemClickListener

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

    fun add(oll: Article){
        output.add(oll)
        notifyDataSetChanged()
    }

    interface onItemClickListener{
        fun onItemClick(v: View, position:Int)
    }

    fun setOnItemClickClickListener(listener: onItemClickListener){
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
        fun bind(output: Article){
            articleId=output.article_id
            Picasso.with(itemView.context)
                .load(output.img_url)
                .transform(RatioTransformation(300))
                .into(img)


            Picasso.with(itemView.context)
                .load(output.ref_icon_url)
                .transform(RatioTransformation(100))
                .into(buttonRef)
            summary.text=output.summary
            //buttonRef.setImageBitmap(output.getRefIcon())
            /*buttonRef.setOnClickListener(View.OnClickListener {
                var intent = Intent(Intent.ACTION_VIEW, Uri.parse(output.link))
                (itemView.parent as Context).startActivity(intent)
            })*/
        }
    }

}