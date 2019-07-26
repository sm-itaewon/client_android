package com.itaewonproject.B

import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.itaewonproject.RecyclerviewAdapter.AdapterRouteList

class RoutesItemTouchHelperCallback (var adapter:AdapterRouteList): ItemTouchHelper.Callback(){
    private var listener:OnItemMoveListener

    init{
        listener = adapter
    }

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        var dragFlag:Int =ItemTouchHelper.UP or ItemTouchHelper.DOWN
        Log.i("!!","layout:${viewHolder.layoutPosition} adapter:${viewHolder.adapterPosition}")
        if(adapter.routes[viewHolder.layoutPosition].opened != null)
        {
            if(!adapter.routes[viewHolder.layoutPosition].opened)
            {
                return makeMovementFlags(dragFlag,ItemTouchHelper.LEFT)
            }else
            {
                return makeMovementFlags(dragFlag,0)
            }
        }else
        {
            return makeMovementFlags(dragFlag,ItemTouchHelper.LEFT)
        }
        //var swipeFlag = ItemTouchHelper.LEFT
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        listener.OnItemMove(viewHolder.adapterPosition,target.adapterPosition)
        return true
    }

    interface OnItemMoveListener{
        fun OnItemMove(from:Int,to:Int):Boolean
        fun OnItemSwipe(pos:Int):Boolean

    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        listener.OnItemSwipe(viewHolder.layoutPosition)
    }

}