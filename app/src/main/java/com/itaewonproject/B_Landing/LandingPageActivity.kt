package com.itaewonproject.B_Landing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.itaewonproject.A.LocationSelectActivity
import com.itaewonproject.B_Mypage.MypageActivity
import com.itaewonproject.R

class LandingPageActivity : AppCompatActivity() {

    lateinit var buttonSearch:Button
    lateinit var buttonMypage:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)
        buttonSearch = findViewById(R.id.but_search)
        buttonMypage = findViewById(R.id.but_mypage)
        buttonMypage.setOnClickListener({
            var intent = Intent(this,MypageActivity::class.java)
            startActivity(intent)
        })
        buttonSearch.setOnClickListener({
            var intent = Intent(this,LocationSelectActivity::class.java)
            startActivity(intent)
        })
    }
}
