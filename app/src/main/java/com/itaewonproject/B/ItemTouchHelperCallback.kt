package com.itaewonproject.B

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.itaewonproject.RecyclerviewAdapter.AdapterRouteList

class ItemTouchHelperCallback (adapter:AdapterRouteList): ItemTouchHelper.Callback(){
    private var listener:OnItemMoveListener

    init{
        listener = adapter
    }

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        var dragFlag:Int =ItemTouchHelper.UP or ItemTouchHelper.DOWN
        var swipeFlag = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(dragFlag,0)
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
    }


    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
    }

}