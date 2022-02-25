package com.example.ecuchange.presentacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ecuchange.R
import com.example.ecuchange.data.api.RetrofitAPI
import com.example.ecuchange.data.api.service.ArticulosService
import com.example.ecuchange.data.api.service.UserService
import com.example.ecuchange.data.api.service.UsuarioService
import com.example.ecuchange.databinding.ActivityRegisterBinding
import com.example.ecuchange.entities.UsuarioModal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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


        binding.botonRegistrar.setOnClickListener() {
//            if (nombre.getText().toString().isEmpty() && apellido.getText().toString().isEmpty() && correo.getText().toString().isEmpty()) {
//                Toast.makeText(this,"APLICACION PROPIEDAD DE MICHAEL BORJA Y ALEX TONATO. CONTACT US",
//                    Toast.LENGTH_SHORT).show()
//            }
            CoroutineScope(Dispatchers.IO).launch {
                postData(binding.txtNombre.text.toString(), binding.txtApellido.text.toString(), binding.txtEmail.text.toString(), binding.txtUsuario.text.toString(), binding.txtPasswordR.text.toString(), binding.txtDireccion.text.toString())
            }

        }
    }

    suspend fun postData (nombre: String, apellido: String, correo: String, user: String, password: String, direccion: String) {

        val modal = UsuarioModal(nombre,apellido,correo, user,password,direccion)
        val service = RetrofitAPI.postUsuariosApi().create(UsuarioService::class.java)

        service.createEmployee(modal)
    }
}