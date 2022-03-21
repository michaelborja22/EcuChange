package com.example.ecuchange.presentacion

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import com.example.ecuchange.data.api.RetrofitAPI
import com.example.ecuchange.data.api.service.UsuarioService
import com.example.ecuchange.databinding.ActivityRegisterBinding
import com.example.ecuchange.entities.UsuarioModal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.botonRegresar.setOnClickListener() {
            var intent = Intent(this, LoginActivity::class.java )
            startActivity(intent)
        }

        binding.registerPrincipal.setOnClickListener(){
            hideSoftkeyboard(binding.registerPrincipal)
        }

        binding.botonRegistrar.setOnClickListener() {
            if (binding.txtNombre.text.isEmpty() ||  binding.txtApellido.text.isEmpty() || binding.txtEmail.text.isEmpty() || binding.txtUsuario.text.isEmpty() || binding.txtPasswordR.text.isEmpty() || binding.telefono.text.isEmpty()) {
                Toast.makeText(this,"Complete los campos",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (binding.telefono.text.length!=10) {
                Toast.makeText(this,"Número de teléfono incorrecto",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(Patterns.EMAIL_ADDRESS.matcher(binding.txtEmail.text).matches()  ){
                Toast.makeText(this,"Correo electrónico invalido",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            CoroutineScope(Dispatchers.IO).launch {
                postData(binding.txtNombre.text.toString(), binding.txtApellido.text.toString(), binding.txtEmail.text.toString(), binding.txtUsuario.text.toString(), binding.txtPasswordR.text.toString(), binding.txtDireccion.text.toString(), binding.telefono.text.toString())
            }


        }
        binding.txtNombre.setOnClickListener() {
            binding.txtNombre.setText("")
        }



    }

    suspend fun postData (nombre: String, apellido: String, correo: String, user: String, password: String, direccion: String, telefono: String) {

        val modal = UsuarioModal(nombre,apellido,correo, user,password,direccion, telefono)
        val service = RetrofitAPI.postUsuariosApi().create(UsuarioService::class.java)

        val response = service.createEmployee(modal)

        if (response.isSuccessful) {
            var intent = Intent(this, LoginActivity::class.java )
            startActivity(intent)
        } else {
            Toast.makeText(this,"Retrofit Error ${response.code().toString()}", Toast.LENGTH_SHORT).show()
        }
    }

    fun hideSoftkeyboard(vista: View){
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(vista.windowToken,0)

    }
}