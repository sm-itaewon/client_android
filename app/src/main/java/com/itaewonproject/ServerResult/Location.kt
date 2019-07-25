package com.itaewonproject.ServerResult

import android.graphics.Bitmap
import android.view.ViewDebug
import com.google.android.gms.maps.model.LatLng
import java.io.Serializable

/*
 category:
 0 - 숙소
 1 - 관광
 2 - 맛집
 3 - 쇼핑
 4 - 액티비티
 */
class Location:Serializable{
    var title=""
    var imgUrl: ArrayList<String>
    var rating=0f
    var placeId=""
    var latitude=0.0
    var longitude=0.0
    var category =0
    var articleCount=0
    var usedTime=0

    constructor(title:String, urls:ArrayList<String>, rating:Float, placeId:String,latitude:Double,longitude:Double,usedTime:Int,category: Int,articleCount:Int){
        this.title=title
        this.imgUrl=urls
        this.rating=rating
        this.placeId=placeId
        this.latitude=latitude
        this.longitude=longitude
        this.usedTime=usedTime
        this.category=category
        this.articleCount=articleCount

    }
    fun latlng():LatLng{return LatLng(latitude,longitude)}

}