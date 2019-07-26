package com.itaewonproject.B

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.InflateException
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.itaewonproject.A.LocationArticleActivity
import com.itaewonproject.APIs
import com.itaewonproject.R
import com.itaewonproject.RecyclerviewAdapter.AdapterLocationList
import com.itaewonproject.RecyclerviewAdapter.AdapterRouteList
import com.itaewonproject.ServerResult.Location
import com.itaewonproject.ServerResult.Route

class RouteListFragment : Fragment(),AdapterRouteList.OnStartDragListener {
    override fun OnStartDrag(viewHolder: RecyclerView.ViewHolder) {
        itemTouchHelper.startDrag(viewHolder)
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var list:ArrayList<Route>
    private lateinit var itemTouchHelper:ItemTouchHelper

    private fun setListViewOption(view:View){
        list = APIs.B_API1(1)

        recyclerView = view.findViewById(R.id.route_RecyclerView) as RecyclerView
        val adapter = AdapterRouteList(view.context, list,this)
        var callback = ItemTouchHelperCallback(adapter)
        itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
        adapter.setOnItemClickClickListener(object: AdapterRouteList.onItemClickListener {
            override fun onItemClick(v: View, position: Int) {
                ///버튼 누르면 다음 프레그먼트 넘어가게 구현!
            }

        })

        recyclerView.adapter=adapter

        val linearLayoutManager= LinearLayoutManager(view.context)
        recyclerView.layoutManager= linearLayoutManager
        recyclerView.setHasFixedSize(true)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setListViewOption(view)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view=view
        try{
            view=inflater.inflate(R.layout.fragment_route_list, container, false)
        }catch (e:InflateException){
            e.printStackTrace()
        }
        return view
    }
}
