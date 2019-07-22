package com.itaewonproject

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
class OutputLocation{
    var title=""
    var imgUrl: ArrayList<String>
    var rating=0f
    var placeId=""
    var latlng = LatLng(0.0,0.0)
    var category =0
    var articleCount=0
    var usedTime=0

    constructor(title:String, urls:ArrayList<String>, rating:Float, placeId:String,latlng: LatLng,usedTime:Int,category: Int,articleCount:Int){
        this.title=title
        this.imgUrl=urls
        this.rating=rating
        this.placeId=placeId
        this.latlng=latlng
        this.usedTime=usedTime
        this.category=category
        this.articleCount=articleCount

    }

}