package com.itaewonproject.RecyclerviewAdapter

import android.content.Context
import android.graphics.Color
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.toDrawable
import androidx.core.view.MotionEventCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.itaewonproject.B.ItemTouchHelperCallback
import com.itaewonproject.R
import com.itaewonproject.ServerResult.Route
import java.util.*
import kotlin.collections.ArrayList

class AdapterRouteList(val context: Context, var routes:ArrayList<Route>,var startDragListener:OnStartDragListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),ItemTouchHelperCallback.OnItemMoveListener {

    private lateinit var listener: onItemClickListener

    override fun OnItemMove(from: Int, to: Int): Boolean {
        Collections.swap(routes,from,to)
        notifyItemMoved(from,to)
        return true
    }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(routes[position].childRoute.isEmpty())
        {
            true -> (holder as SingleViewHolder).bind(position)
            false -> (holder as GroupViewHolder).bind(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType==0) SingleViewHolder(LayoutInflater.from(context).inflate(R.layout.list_route_single, parent, false))
            else GroupViewHolder(LayoutInflater.from(context).inflate(R.layout.list_route_group, parent, false))
    }

    override fun getItemCount(): Int {
        return routes.size
    }

    override fun getItemViewType(position: Int): Int {
        if (routes[position].childRoute.isEmpty()) return 0
        else return 1
    }

    interface onItemClickListener{
        fun onItemClick(v: View, position:Int)
    }

    interface OnStartDragListener{
        fun OnStartDrag(viewHolder:RecyclerView.ViewHolder)
        //fun OnStartDrag(viewHolder:GroupViewHolder)
    }

    fun setOnItemClickClickListener(listener: onItemClickListener){
        this.listener=listener
    }

    inner class SingleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private var drag:ImageView
        private var title:EditText
        private var location:TextView
        private var updated:TextView
        init{
            drag = itemView.findViewById(R.id.image_drag) as ImageView
            title = itemView.findViewById(R.id.text_title) as EditText
            location = itemView.findViewById(R.id.text_location) as TextView
            updated = itemView.findViewById(R.id.text_updated) as TextView

            itemView.setOnClickListener(View.OnClickListener {
                val pos = adapterPosition
                if(pos!=RecyclerView.NO_POSITION){
                    if(listener!=null){
                        listener.onItemClick(it,pos)
                    }
                }
            })


        }

        fun bind(pos:Int){
            var route = routes[pos]
            title.text = Editable.Factory.getInstance().newEditable(route.title)
            location.text=route.location
            updated.text=route.updated

            drag.setOnTouchListener({ view: View, motionEvent: MotionEvent ->
                if(MotionEventCompat.getActionMasked(motionEvent)==MotionEvent.ACTION_DOWN){
                    startDragListener.OnStartDrag(this)
                }
                false
            })
        }

    }

    inner class GroupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),AdapterRouteList.OnStartDragListener {
        override fun OnStartDrag(viewHolder: RecyclerView.ViewHolder) {
            itemTouchHelper.startDrag(viewHolder)
        }

        private var drag:ImageView
        private var folder:ImageView
        private var title:EditText

        private var location:TextView
        private var updated:TextView
        private var background:ConstraintLayout
        private var recyclerView:RecyclerView
        private lateinit var itemTouchHelper:ItemTouchHelper
        init{

            drag = itemView.findViewById(R.id.image_drag) as ImageView
            folder = itemView.findViewById(R.id.image_folder) as ImageView
            title = itemView.findViewById(R.id.text_title) as EditText
            location = itemView.findViewById(R.id.text_location) as TextView
            updated = itemView.findViewById(R.id.text_updated) as TextView
            background = itemView.findViewById(R.id.background) as ConstraintLayout
            recyclerView = itemView.findViewById(R.id.RecyclerView) as RecyclerView
            recyclerView.visibility=View.GONE

            background.setOnClickListener({
                if(recyclerView.visibility==View.GONE) {
                    recyclerView.visibility=View.VISIBLE
                    background.background = Color.GRAY.toDrawable()
                    folder.setImageResource(R.drawable.ic_folder_open_black_24dp)
                }
                else {
                    recyclerView.visibility=View.GONE
                    background.background = Color.LTGRAY.toDrawable()
                    folder.setImageResource(R.drawable.ic_folder_black_24dp)

                }
            })

        }

        fun bind(pos:Int){
            var route = routes[pos]
            title.text = Editable.Factory.getInstance().newEditable(route.title)
            location.text=route.location
            updated.text=route.updated

            val adapter = AdapterRouteList(itemView.context, routes[pos].childRoute,this)
            var callback = ItemTouchHelperCallback(adapter)

            itemTouchHelper = ItemTouchHelper(callback)
            itemTouchHelper.attachToRecyclerView(recyclerView)

            adapter.setOnItemClickClickListener(object: onItemClickListener {
                override fun onItemClick(v: View, position: Int) {
                    Log.i("!!","!!")
                }

            })

            recyclerView.adapter=adapter

            val linearLayoutManager= LinearLayoutManager(itemView.context)
            recyclerView.layoutManager= linearLayoutManager
            recyclerView.setHasFixedSize(true)

            drag.setOnTouchListener({ view: View, motionEvent: MotionEvent ->
                if(MotionEventCompat.getActionMasked(motionEvent)==MotionEvent.ACTION_DOWN){
                    startDragListener.OnStartDrag(this)
                }
                false
            })

        }
    }

}