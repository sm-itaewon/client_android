package com.itaewonproject.B

import android.os.Bundle
import android.view.InflateException
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.itaewonproject.R

class RouteEditFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
