package com.itaewonproject

import android.graphics.Bitmap
import java.net.URL

class OutputArticle{
    var img_url =""
    var summary=""
    var ref_icon_url=""
    var link=""
    var article_id=""
    constructor(img_url: String,summary:String,ref_icon_url:String,article_id:String,link:String){
        this.img_url=img_url
        this.summary=summary
        this.ref_icon_url=ref_icon_url
        this.article_id=article_id
        this.link=link
    }
    /*fun getImage(): Bitmap {
        return APIs.BitmapFromURL(img_url,300,300)
    }
    fun getRefIcon():Bitmap{
        return APIs.BitmapFromURL(ref_icon_url,100,100)
    }*/
}