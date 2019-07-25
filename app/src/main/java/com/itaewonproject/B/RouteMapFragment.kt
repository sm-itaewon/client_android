package com.itaewonproject.B

import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.InflateException
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.Marker
import com.google.android.material.tabs.TabLayout
import com.itaewonproject.A.LocationListActivity
import com.itaewonproject.R
import kotlinx.android.synthetic.main.fragment_route_map.*

class RouteMapFragment : Fragment(),OnMapReadyCallback {

    private lateinit var mMap:GoogleMap
    private lateinit var mapView:MapView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mapView = view.findViewById(R.id.map) as MapView
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
    }

    override fun onResume() {
        mapView.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        mapView.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        mapView.onLowMemory()
        super.onLowMemory()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        MapsInitializer.initialize(this.activity)
        mMap=googleMap

        mMap.setOnMapClickListener(GoogleMap.OnMapClickListener(){
            mMap.clear()
            //mMap.addMarker(it)
            mMap.animateCamera(CameraUpdateFactory.newLatLng(it))

        })
        /*mMap.setOnMarkerClickListener { it: Marker? ->
            var intent = Intent(this, LocationListActivity::class.java)

            if (it != null) {
                intent.putExtra("LatLng",it.position);
                intent.putExtra("Altitude",mMap.cameraPosition.zoom)
            }
            startActivity(intent)

            return@setOnMarkerClickListener false
        }*/

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view=view
        try {
            view = inflater.inflate(R.layout.fragment_route_map, container, false)
        }catch (e: InflateException){
            e.printStackTrace()
        }
        return view
    }
}
