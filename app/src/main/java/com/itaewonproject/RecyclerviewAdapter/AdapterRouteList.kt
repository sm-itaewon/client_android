package com.itaewonproject.RecyclerviewAdapter

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.toDrawable
import androidx.core.view.MotionEventCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.itaewonproject.B_Mypage.RouteListFragment
import com.itaewonproject.B_Mypage.RoutesItemTouchHelperCallback
import com.itaewonproject.R
import com.itaewonproject.ServerResult.Route
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class AdapterRouteList(val context: Context,route:ArrayList<Route>,var startDragListener:OnStartDragListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    RoutesItemTouchHelperCallback.OnItemMoveListener{
    override fun OnShake(pos: Int, date: Date) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun OnMerge(from: Int, to: Int, date: Date) {
        routes.makeFolder(from,to)
        /*if((Date().time-date.time)>500)
        {

        }else
        {
            if(routes.isChild(from)&&!routes.i)
        }*/
        notifyDataSetChanged()
    }

    private lateinit var listener: onItemClickListener
    var routes = RouteListManager(route)

    /*override fun OnItemDrag(from: Int, to: Int, date: Date): Boolean {
        Log.i("!!","${date.seconds}, ${Date().seconds}, ${Date().time-date.time}")
        if((Date().time-date.time)>1000)
        {
            //Log.i("!!","from:$from , to:$to")
            if(routes[from].childRoute.isEmpty()){
                routes[to].childRoute.add(routes[from])
            }else
            {
                routes[to].childRoute.addAll(routes[from].childRoute)
            }
            routes.removeAt(from)
            notifyDataSetChanged()
        }
        return true
    }*/
/*
    override fun OnItemMove(from: Int, to: Int): Boolean {
        Log.i("!!","$from,$to")
        Collections.swap(routes,from,to)
        notifyItemMoved(from,to)
        return true
    }*/

    override fun OnItemSwipe(pos: Int): Boolean {
        Log.i("Removing","$pos, ${routes[pos].title}")
        routes.removeAt(pos)
        notifyItemRemoved(pos)
        //notifyDataSetChanged()
        return true
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BaseViewHolder).bind(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType==0) SingleViewHolder(LayoutInflater.from(context).inflate(R.layout.list_route_single, parent, false))
            else if(viewType==1) GroupViewHolder(LayoutInflater.from(context).inflate(R.layout.list_route_group, parent, false))
            else AddViewHolder(LayoutInflater.from(context).inflate(R.layout.list_route_add, parent, false))
    }

    override fun getItemCount(): Int {
        return routes.size +1
    }

    override fun getItemViewType(position: Int): Int {
        if(position==routes.size) return 2
        else if (routes.isFolder(position)) return 1
        else return 0
    }

    interface onItemClickListener{
        fun onItemClick(v: View, position:Int)
    }

    interface OnStartDragListener{
        fun OnStartDrag(viewHolder:RecyclerView.ViewHolder)
    }

    fun setOnItemClickClickListener(listener: onItemClickListener){
        this.listener=listener
    }

    inner class SingleViewHolder(itemView: View) : BaseViewHolder(itemView){

        private var drag:ImageView
        private var title:TextView
        private var location:TextView
        private var updated:TextView
        private var background:ConstraintLayout

        init{
            drag = itemView.findViewById(R.id.image_drag) as ImageView
            title = itemView.findViewById(R.id.text_title) as TextView
            location = itemView.findViewById(R.id.text_location) as TextView
            updated = itemView.findViewById(R.id.text_updated) as TextView
            background = itemView.findViewById(R.id.background) as ConstraintLayout
        }

        override fun bind(pos:Int){
            var route = routes[pos]
            title.text = Editable.Factory.getInstance().newEditable(route.title)
            location.text=route.location
            updated.text=route.updated
            itemView.setOnClickListener(View.OnClickListener {
                val pos = adapterPosition
                if(pos!=RecyclerView.NO_POSITION){
                    //routeFragment.toEditFragment(pos)
                }
            })
            if(routes[pos].opened) {
                background.background = Color.LTGRAY.toDrawable()
            }
            else {
                background.background = Color.WHITE.toDrawable()
            }

            drag.setOnTouchListener({ view: View, motionEvent: MotionEvent ->
                if(MotionEventCompat.getActionMasked(motionEvent)==MotionEvent.ACTION_DOWN){
                    startDragListener.OnStartDrag(this)
                }
                false
            })
        }
    }

    inner class AddViewHolder(itemView: View) :BaseViewHolder(itemView) {
        private var buttonAdd: ImageView
        init{
            buttonAdd = itemView.findViewById(R.id.button_add) as ImageView
        }
        override fun bind(pos:Int){
            buttonAdd.setOnClickListener({

            })
        }
    }

    inner class GroupViewHolder(itemView: View) : BaseViewHolder(itemView),OnStartDragListener {
        private var drag:ImageView
        private var folder:ImageView
        private var textTitle:TextView
        private var editTitle:EditText
        private var location:TextView
        private var updated:TextView
        private var background:ConstraintLayout
        private lateinit var itemTouchHelper:ItemTouchHelper


        override fun OnStartDrag(viewHolder: RecyclerView.ViewHolder) {
            itemTouchHelper.startDrag(viewHolder)
        }

        init{

            drag = itemView.findViewById(R.id.image_drag) as ImageView
            folder = itemView.findViewById(R.id.image_folder) as ImageView
            textTitle = itemView.findViewById(R.id.text_title) as TextView
            editTitle = itemView.findViewById(R.id.edit_title) as EditText
            location = itemView.findViewById(R.id.text_location) as TextView
            updated = itemView.findViewById(R.id.text_updated) as TextView
            background = itemView.findViewById(R.id.background) as ConstraintLayout

            editTitle.visibility=View.INVISIBLE
            textTitle.visibility=View.VISIBLE
            drag.visibility=View.VISIBLE
        }

        override fun bind(pos:Int){
            var route = routes[pos]
            textTitle.text=route.title
            editTitle.text = Editable.Factory.getInstance().newEditable(route.title)
            location.text=route.location
            updated.text=route.updated

            if(routes[pos].opened) {
                background.background = Color.LTGRAY.toDrawable()
            }
            else {
                background.background = Color.WHITE.toDrawable()
            }

            background.setOnClickListener({
                routes.open(pos)
                if(routes[pos].opened) {
                    background.background = Color.LTGRAY.toDrawable()
                    folder.setImageResource(R.drawable.ic_folder_open_black_24dp)
                    editTitle.visibility=View.VISIBLE
                    textTitle.visibility=View.INVISIBLE
                    drag.visibility=View.INVISIBLE
                }
                else {
                    background.background = Color.WHITE.toDrawable()
                    folder.setImageResource(R.drawable.ic_folder_black_24dp)
                    editTitle.visibility=View.INVISIBLE
                    textTitle.visibility=View.VISIBLE
                    drag.visibility=View.VISIBLE
                }
                notifyDataSetChanged()
            })


            drag.setOnTouchListener({ view: View, motionEvent: MotionEvent ->
                if(MotionEventCompat.getActionMasked(motionEvent)==MotionEvent.ACTION_DOWN){
                    startDragListener.OnStartDrag(this)
                }
                false
            })

        }
    }

    inner class RouteListManager(routes:ArrayList<Route>): LinkedList<Route>() {
        var child : HashSet<Int>
        init{
            this.addAll(routes)
            child = hashSetOf()
        }

        fun isChild(pos:Int):Boolean{
            return child.contains(this[pos].RouteID)
        }

        fun getParent(pos:Int):Int{
            for( i in pos..0)
                if(!isChild(i)) return i
            return -1

        }

        fun makeFolder(from:Int,to:Int){
            Log.i("!!!mf","${isChild(from)} ${isChild(to)}")
            if(!isChild(from) && !isChild(to)){// from, to 둘다 흰배경 안열림
                if(isFolder(from)){
                    this[to].childRoute.addAll(this[from].childRoute)
                    this.removeAt(from)
                }else
                {
                    this[to].childRoute.add(this[from])
                    this.removeAt(from)
                }
            }else if(isChild(from) && !isChild(to)){ //from은 검정배경의 자식 아이템, to는 안열림
                var par = getParent(from)
                if(par>-1){
                    var r= this[from]
                    open(par)
                    this[par].childRoute.remove(r)
                    this.add(par+1,r)
                    open(par)
                }
            }else if(!isChild(from) && isChild(to))//from은 안열린 상태(폴더,일반), to는 자식 아이템
            {
                var par = getParent(to)
                if(par>-1) {
                    open(par)
                    if(isFolder(from)){
                        this[par].childRoute.addAll(this[from].childRoute)
                        this.removeAt(from)
                    }else
                    {
                        this[to].childRoute.add(this[from])
                        this.removeAt(from)
                    }
                    open(par)
                }
            }else//from, to 둘 다 열린 상태
            {
                var fpar = getParent(from)
                var tpar = getParent(to)
                if(fpar>-1 && tpar>-1){
                    var r= this[from]
                    open(fpar)
                    open(tpar)
                    this[fpar].childRoute.remove(r)
                    this[tpar].childRoute.add(r)
                    open(fpar)
                    open(tpar)
                }
            }

        }

        fun isFolder(pos:Int):Boolean{
            return this[pos].childRoute.size>0
        }

        fun open(pos:Int){
            if(isFolder(pos))
            {
                this[pos].opened = !this[pos].opened
                if(this[pos].opened) {
                    for(r in this[pos].childRoute){
                        r.opened=true
                        child.add(r.RouteID)
                    }
                    this.addAll(pos + 1, this[pos].childRoute)
                }else{
                    this.removeAll(this[pos].childRoute)
                    for(r in this[pos].childRoute){
                        r.opened=false
                        child.remove(r.RouteID)
                    }
                }
            }
        }

        fun isOpened(pos:Int):Boolean{
            return isFolder(pos) && this[pos].opened
        }

    }

}