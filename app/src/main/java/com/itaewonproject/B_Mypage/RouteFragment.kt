package com.itaewonproject.B_Mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.itaewonproject.R


class RouteFragment : Fragment() {

    //private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //tabLayout = view.findViewById(R.id.tabLayout) as TabLayout
        viewPager = view.findViewById(R.id.viewPager) as ViewPager
        var adapter = TabPagerAdapter(childFragmentManager!!,3)
        adapter.addPage(RouteListFragment(),"List")
        adapter.addPage(RouteEditFragment(),"Edit")
        adapter.addPage(RouteMapFragment(),"Map")
        viewPager.adapter = adapter
        //tabLayout.setupWithViewPager(viewPager)
        /*val fragmentTransaction = fragmentManager!!.beginTransaction()
        var fragment = RouteListFragment()
        fragmentTransaction.add(R.id.fragment, fragment) // add 대신 replace도 가능
        fragmentTransaction.commit()*/
    }

  /*  fun toEditFragment(pos:Int){
        var fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.replace(R.id.fragment,RouteEditFragment())
        fragmentTransaction.commit()
        Log.i("!!~~","!!!")
    }*/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_route, container, false)
    }
}
