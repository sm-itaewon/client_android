package com.itaewonproject.B_Landing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import com.itaewonproject.R

class LoginActivity : AppCompatActivity() {

    lateinit var editID:EditText
    lateinit var editPW:EditText
    lateinit var buttonLogin:Button
    lateinit var buttonLoginKaKao:Button
    lateinit var buttonSignin:Button
    lateinit var checkAutoLogin:CheckBox
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        editID = findViewById(R.id.edit_id) as EditText
        editPW=findViewById(R.id.edit_password) as EditText
        buttonLogin = findViewById(R.id.button_login) as Button
        buttonLoginKaKao =findViewById(R.id.button_signin_kakao) as Button
        buttonSignin = findViewById(R.id.button_signin) as Button

        buttonLogin.setOnClickListener({
            var intent = Intent(this,LandingPageActivity::class.java)
            startActivity(intent)
            finish()
        })

        buttonSignin.setOnClickListener({
            var intent = Intent(this,SignInActivity::class.java)
            startActivity(intent)
        })
    }
}
