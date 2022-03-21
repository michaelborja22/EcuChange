package com.example.ecuchange.presentacion

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.ecuchange.R
import com.example.ecuchange.data.database.entidades.UsuarioEntity
import com.example.ecuchange.databinding.ActivityLoginBinding
import com.example.ecuchange.logica.UsuarioLogica
import com.example.ecuchange.utils.EcuChange
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding:ActivityLoginBinding
    private var access: Boolean = false
    private lateinit var usuarioAcces: UsuarioEntity

    override fun onCreate(savedInstanceState: Bundle?) {

    super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.botonLogin.setOnClickListener(){
            val dbSh = this.getSharedPreferences("dataUser", Context.MODE_PRIVATE)
            var editor = dbSh.edit()
            CoroutineScope(Dispatchers.Main).launch {
                // access = UsuarioLogica().LoginUser(binding.txtEmail.text.toString(),binding.txtPassword.text.toString())
                usuarioAcces = UsuarioLogica().LoginUser(binding.txtEmail.text.toString(),binding.txtPassword.text.toString())
                if(usuarioAcces.user === "admin"){
                    binding.layoutEmail.error = "Ingrese un usuario valido"
                    binding.layoutPassword.error = "Contraseña incorrecta"
                }else{
                    println(usuarioAcces.id)
                    editor.putString("id_User", usuarioAcces.id)
                    editor.commit()
                    binding.layoutEmail.error=""
                    var intent = Intent(applicationContext, PrincipalActivity::class.java)
                    startActivity(intent)
                }

            }
        }

        binding.botonRegistro.setOnClickListener() {
            var intent = Intent(this, RegisterActivity::class.java )
            startActivity(intent)
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

    override fun onBackPressed(){
        var intent = Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    fun hideSoftkeyboard(vista: View){
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(vista.windowToken,0)

    }


}

