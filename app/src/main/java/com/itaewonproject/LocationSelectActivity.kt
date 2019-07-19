package com.itaewonproject

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.places.PlaceLikelihoodBufferResponse
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.internal.impl.net.pablo.PlaceResult
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import java.io.IOException
import java.util.*


// 권한 요청하게 만들기
class LocationSelectActivity : AppCompatActivity(),OnMapReadyCallback{



    private lateinit var mMap: GoogleMap
    private lateinit var location: Location
    private lateinit var geocoder:Geocoder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_select)

        Places.initialize(applicationContext,"AIzaSyCQBy7WzSBK-kamsMKt6Yk1XpxirVKiW8A")
        var placesClient = Places.createClient(this) as PlacesClient
        geocoder = Geocoder(this)

        val mapFragment = fragmentManager.findFragmentById(R.id.map) as MapFragment
        mapFragment.getMapAsync(this)

        val autoCompleteSupportFragment = supportFragmentManager.findFragmentById(R.id.autocomplete_location_search) as AutocompleteSupportFragment
        autoCompleteSupportFragment.setPlaceFields(Arrays.asList(Place.Field.ID,Place.Field.NAME,Place.Field.LAT_LNG))



        autoCompleteSupportFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                // TODO: Get info about the selected place.

                if (place.latLng != null) {
                    mMap.clear()
                    Log.i("!!",place.name)
                    mMap.addMarker(getMarkerOption(place.latLng!!))
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(place.latLng))
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(15f))
                }
            }

            override fun onError(status: Status) {
                // TODO: Handle the error.
            }
        })
    }

    private fun getMarkerOption(latLng: LatLng):MarkerOptions{
        var marker = MarkerOptions()
        var result=listOf<Address>()
        var place:Place
        marker.position(latLng)

        try{
            result = geocoder.getFromLocation(latLng.latitude,latLng.longitude,1)
            if(result.size>0){
                marker.snippet(result[0].getAddressLine(0))
                marker.title(result[0].featureName)

            }
        }catch (e:IOException){
            e.printStackTrace()
        }catch (e:IndexOutOfBoundsException){
            e.printStackTrace()
        }


        return marker
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap=googleMap
        location = Location("Seoul")
        location.longitude = 37.56
        location.latitude=126.97

        getDeviceLocation()


        mMap.setOnMapClickListener(GoogleMap.OnMapClickListener(){
            mMap.clear()
            mMap.addMarker(getMarkerOption(it))
            mMap.animateCamera(CameraUpdateFactory.newLatLng(it))

        })
        mMap.setOnPoiClickListener(GoogleMap.OnPoiClickListener {
            Log.i("!!","${it.name} ${it.placeId} ${it.latLng}")
        })

        mMap.setOnMarkerClickListener { it: Marker? ->
            var intent = Intent(this,LocationListActivity::class.java)

            if (it != null) {
                intent.putExtra("LatLng",it.position);
                intent.putExtra("Altitude",mMap.cameraPosition.zoom)
            }
            startActivity(intent)

            return@setOnMarkerClickListener false
        }

    }

    fun getDeviceLocation(){
        val mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        try{
            var locationResult = mFusedLocationProviderClient.lastLocation as Task<Location>
            locationResult.addOnCompleteListener(this, OnCompleteListener(function = fun(it: Task<Location>) {
                if(it.isComplete){
                    location = it.result!!
                }
                mMap.moveCamera(CameraUpdateFactory.newLatLng(LatLng(location.latitude,location.longitude)))
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15f))
            }))
        }catch(se:SecurityException){

        }
    }




}

