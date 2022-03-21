package com.example.ecuchange.presentacion

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ecuchange.R
import com.example.ecuchange.data.api.RetrofitAPI
import com.example.ecuchange.data.api.service.UsuarioService
import com.example.ecuchange.databinding.ActivityCambioPassword2Binding
import com.example.ecuchange.entities.UsuarioModal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CambioPassword : AppCompatActivity() {

    private lateinit var binding: ActivityCambioPassword2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCambioPassword2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.botonRestablecer.setOnClickListener() {
            if (binding.newPassword.text.toString() != binding.newPasswordConfirm.text.toString()) {
                Toast.makeText(this,"Las contraseñas no coinciden",
                    Toast.LENGTH_SHORT).show()
                binding.layoutPassword.error = "Las contraseñas no coinciden"
                binding.layoutPasswordConfirm.error = "Las contraseñas no coinciden"
                return@setOnClickListener
            }
            CoroutineScope(Dispatchers.IO).launch {
                updatePassword(binding.newPassword.text.toString())
            }
        }
    }

    suspend fun updatePassword (password: String) {

        val modal = UsuarioModal(password)
        val service = RetrofitAPI.postUsuariosApi().create(UsuarioService::class.java)
        val dbSh = this.getSharedPreferences("dataUser", Context.MODE_PRIVATE)
        var id = dbSh.getString("id_User", "")
        val response = service.editarUsuario(id.toString(), modal)

        if (response.isSuccessful) {
            var intent = Intent(this, PrincipalActivity::class.java )
            startActivity(intent)
        } else {
            Toast.makeText(this,"Retrofit Error ${response.code().toString()}", Toast.LENGTH_SHORT).show()
        }
    }
}