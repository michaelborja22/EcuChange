package com.example.ecuchange

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var botonLogin = findViewById<Button>(R.id.botonLogin)
        var layoutEmail = findViewById<TextInputLayout>(R.id.layoutEmail)
        var txtEmail = findViewById<TextView>(R.id.txtEmail)

        botonLogin.setOnClickListener(){
            if(txtEmail.text.toString().trim()==""){
                layoutEmail.error = getString(R.string.error)
            }else{
                layoutEmail.error=""
                var intent = Intent(this, PrincipalActivity::class.java)
                startActivity(intent)
            }
        }


    }
}