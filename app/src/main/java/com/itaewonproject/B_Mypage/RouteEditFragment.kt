package com.itaewonproject.B_Mypage

import android.os.Bundle
import android.view.InflateException
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.itaewonproject.APIs
import com.itaewonproject.R
import com.itaewonproject.RecyclerviewAdapter.AdapterRouteEdit
import com.itaewonproject.ServerResult.Location

class RouteEditFragment : Fragment(),AdapterRouteEdit.OnStartDragListener  {

    override fun OnStartDrag(viewHolder: RecyclerView.ViewHolder) {
        itemTouchHelper.startDrag(viewHolder)
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var list:ArrayList<Location>
    private lateinit var itemTouchHelper: ItemTouchHelper

    private fun setListViewOption(view:View){
        list = APIs.API1(12.333333,123.333333,14f)

        recyclerView = view.findViewById(R.id.edit_recyclerView) as RecyclerView
        val adapter = AdapterRouteEdit(view.context, list,this)
        var callback = EditItemTouchHelperCallback(adapter)
        itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
        adapter.setOnItemClickClickListener(object: AdapterRouteEdit.onItemClickListener {
            override fun onItemClick(v: View, position: Int) {
                ///버튼 누르면 다음 프레그먼트 넘어가게 구현!
                //par.toEditFragment(position)
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
            view=inflater.inflate(R.layout.fragment_route_edit, container, false)
        }catch (e: InflateException){
            e.printStackTrace()
        }
        return view
    }
}
