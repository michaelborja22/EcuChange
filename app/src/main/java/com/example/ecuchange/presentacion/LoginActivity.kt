package com.example.ecuchange.presentacion

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.ecuchange.R
import com.example.ecuchange.databinding.ActivityLoginBinding
import com.example.ecuchange.logica.UsuarioLogica

class LoginActivity : AppCompatActivity() {

    private lateinit var binding:ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {

    super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.botonLogin.setOnClickListener(){
            val access = UsuarioLogica().LoginUser(binding.txtEmail.text.toString(),binding.txtPassword.text.toString())
            if(access){
                binding.layoutEmail.error = getString(R.string.error)
            }else{
                binding.layoutEmail.error=""
                var intent = Intent(this, PrincipalActivity::class.java)
                startActivity(intent)
            }


        }

        binding.botonPoliticaPrivacidad.setOnClickListener(){
            Toast.makeText(this,"APLICACION PROPIEDAD DE MICHAEL BORJA Y ALEX TONATO. CONTACT US",Toast.LENGTH_SHORT).show()
        }

        binding.botonPoliticaPrivacidad.setOnClickListener(){
            Toast.makeText(this,"APLICACION PROPIEDAD DE MICHAEL BORJA Y ALEX TONATO. CONTACT US",Toast.LENGTH_SHORT).show()
        }

        binding.loginPrincipal.setOnClickListener(){
            hideSoftkeyboard(binding.loginPrincipal)
        }

/*
Antes
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
*/

    }

    fun hideSoftkeyboard(vista: View){
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(vista.windowToken,0)


    }


}