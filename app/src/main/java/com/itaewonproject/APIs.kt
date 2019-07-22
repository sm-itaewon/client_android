package com.itaewonproject

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import com.google.android.gms.maps.model.LatLng
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

object APIs{
    fun API1(longitude:Double, latitude:Double, zoom:Float):ArrayList<OutputLocation>{
        val apiResult = """
            {
                "locationList": [
                    {
                        "title": "",
                         "rating": "",
                         "images": [
                            {
                            "url": ..,
                            }
                         ]
                        "article_id":
                        "url": "https://",
                    },
                    {
                        "url": "",
                    },
                    {
                        "url" "",
                    },
                ],
                "error": null
            }
        """.trimIndent()


        val bmp1 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcStn_0SD5sQvwVChtojSWcxUR5Hkw5JMw4bNJsuoSiI2A1sahk49Q"
        val bmp2 = "https://www.fcbarcelona.com/photo-resources/fcbarcelona/photo/2019/04/27/2b3900dc-06d6-417c-906e-477241e279a8/mini_2019-04-27-BARCELONA-LEVANTE-69.JPG?width=1200&height=750"
        val bmp3 = "https://us.123rf.com/450wm/pandavector/pandavector1705/pandavector170502056/77588042-stock-illustration-multicolored-inable-balls-party-and-parties-single-icon-in-cartoon-style-rater-bitmap-symbol-stock-i.jpg?ver=6"
        val bmp4 = "https://us.123rf.com/450wm/pandavector/pandavector1705/pandavector170501946/77588232-%EB%85%B8%EB%9E%80%EC%83%89-%EC%86%9C-%ED%84%B8-%EB%B3%91%EC%95%84%EB%A6%AC-%EB%B6%80%ED%99%9C%EC%A0%88-%EB%8B%A8%EC%9D%BC-%EC%95%84%EC%9D%B4%EC%BD%98-%EB%A7%8C%ED%99%94-%EC%8A%A4%ED%83%80%EC%9D%BC-rater-%EB%B9%84%ED%8A%B8-%EB%A7%B5-%EA%B8%B0%ED%98%B8-%EC%A3%BC%EC%8B%9D-%EA%B7%B8%EB%A6%BC%EC%9E%85%EB%8B%88%EB%8B%A4-.jpg?ver=6"
        val bmp5 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTQRSot03kQt2dLn0RdPM_18910imxh5xydnfj8quawFqKl4El5WQ"
        val output1 = OutputLocation("output1",arrayListOf<String>(bmp1,bmp2,bmp3,bmp4,bmp5,bmp1,bmp2,bmp3,bmp4,bmp5),3.5f,"1", LatLng(latitude,longitude+0.001),3000,0,10)
        val output2 = OutputLocation("output2",arrayListOf<String>(bmp4,bmp5,bmp3),1.5f,"2", LatLng(latitude,longitude-0.001),4560,1,5)
        val output3 = OutputLocation("output3",arrayListOf<String>(bmp2,bmp3,bmp4,bmp5,bmp1,bmp2,bmp3,bmp4,bmp5),3.5f,"3", LatLng(latitude+0.001,longitude+0.00),3500,2,50)
        val output4 = OutputLocation("output4",arrayListOf<String>(bmp5),4.0f,"4", LatLng(latitude-0.001,longitude+0.0),4888,3,47)
        val output5 = OutputLocation("output5",arrayListOf<String>(bmp2,bmp4,bmp1,bmp4,bmp5,bmp1,bmp2,bmp3),5.0f,"5", LatLng(latitude+0.001,longitude+0.001),12340,4,33)
        var arr = arrayListOf<OutputLocation>(output1,output2,output3,output4,output5)
        for(i in 6..20){
            arr.add(OutputLocation("output$i",arrayListOf<String>(bmp2,bmp4,bmp1,bmp4,bmp5,bmp1,bmp2,bmp4),(i%6)+0f,"$i",LatLng(latitude-0.002,longitude+0.001*i-0.012),i*400,i%5,(i*24)%49))

        }
        return arr
    }

    fun API2(placeID:String):ArrayList<OutputArticle>{
        var bmp = listOf<String>( "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcStn_0SD5sQvwVChtojSWcxUR5Hkw5JMw4bNJsuoSiI2A1sahk49Q"
            , "https://learn.corel.com/wp-content/uploads/2015/11/2014-11-03_1308.png"
            ,"https://us.123rf.com/450wm/pandavector/pandavector1705/pandavector170502056/77588042-stock-illustration-multicolored-inable-balls-party-and-parties-single-icon-in-cartoon-style-rater-bitmap-symbol-stock-i.jpg?ver=6"
            ,"https://us.123rf.com/450wm/pandavector/pandavector1705/pandavector170501946/77588232-%EB%85%B8%EB%9E%80%EC%83%89-%EC%86%9C-%ED%84%B8-%EB%B3%91%EC%95%84%EB%A6%AC-%EB%B6%80%ED%99%9C%EC%A0%88-%EB%8B%A8%EC%9D%BC-%EC%95%84%EC%9D%B4%EC%BD%98-%EB%A7%8C%ED%99%94-%EC%8A%A4%ED%83%80%EC%9D%BC-rater-%EB%B9%84%ED%8A%B8-%EB%A7%B5-%EA%B8%B0%ED%98%B8-%EC%A3%BC%EC%8B%9D-%EA%B7%B8%EB%A6%BC%EC%9E%85%EB%8B%88%EB%8B%A4-.jpg?ver=6"
            ,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTQRSot03kQt2dLn0RdPM_18910imxh5xydnfj8quawFqKl4El5WQ")
        var ref = listOf<String>("https://facebookbrand.com/wp-content/themes/fb-branding/assets/images/fb-logo.png?v2",
            "https://instagram-brand.com/wp-content/uploads/2016/11/Instagram_AppIcon_Aug2017.png?w=300")
        var link = listOf<String>("http://www.facebook.com","http://www.instagram.com")
        var arr = ArrayList<OutputArticle>()
        for(i in 1.. 10){
            arr.add(OutputArticle(bmp[i%5],"summary\nof\nArticle$i",ref[i%2],"$i",link[i%2]))
        }

        return arr
    }


    fun BitmapFromURL(str: String,x:Int,y:Int): Bitmap {
        val url = URL(str)
        val conn = url.openConnection() as HttpURLConnection
        var bmp = Bitmap.createBitmap(x,y,Bitmap.Config.ARGB_8888)

        try{
            conn.doInput=true
            conn.connect()

            val inputStream = conn.inputStream
            bmp = BitmapFactory.decodeStream(inputStream)
        }catch(e:MalformedURLException){

        }catch(e:IOException){

        }
        bmp = Bitmap.createScaledBitmap(bmp,x,y,false)
        return bmp
    }

    fun BitmapFromURL(url: URL,x:Int,y:Int): Bitmap {
        val conn = url.openConnection() as HttpURLConnection
        var bmp = Bitmap.createBitmap(x,y,Bitmap.Config.ARGB_8888)

        try{
            conn.doInput=true
            conn.connect()

            val inputStream = conn.inputStream
            bmp = BitmapFactory.decodeStream(inputStream)
        }catch(e:MalformedURLException){

        }catch(e:IOException){

        }
        bmp = Bitmap.createScaledBitmap(bmp,x,y,false)
        return bmp
    }

    fun secToString(sec:Int):String {
        var ret = ""
        if(sec>=3600) ret+="${sec / 3600}시간"
        if (sec % 3600 != 0 || sec==0) ret += "${(sec % 3600) / 60}분"
        return ret
    }
    fun getCategoryImage(num:Int):Bitmap{
        var bmp = Bitmap.createBitmap(50,50,Bitmap.Config.ARGB_8888)
        when(num){
            0 -> bmp.eraseColor(Color.CYAN)
            1 -> bmp.eraseColor(Color.GREEN)
            2 -> bmp.eraseColor(Color.MAGENTA)
            3 -> bmp.eraseColor(Color.RED)
            4 -> bmp.eraseColor(Color.YELLOW)
        }
        return bmp
    }

}