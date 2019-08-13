package com.itaewonproject.RecyclerviewAdapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    abstract fun bind(pos:Int);
}