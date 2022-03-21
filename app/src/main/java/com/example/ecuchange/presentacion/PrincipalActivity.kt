package com.example.ecuchange.presentacion

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.ecuchange.R
import com.example.ecuchange.data.database.entidades.UsuarioEntity
import com.example.ecuchange.databinding.ActivityPrincipalBinding
import com.example.ecuchange.logica.UsuarioLogica
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import androidx.fragment.app.Fragment as Fragment

class PrincipalActivity : AppCompatActivity() {

    private lateinit var binding: com.example.ecuchange.databinding.ActivityPrincipalBinding
    private var lstFragments = mutableListOf<Int>()
    private lateinit var oneUser: UsuarioEntity
    var bundle: Bundle = Bundle()
    var botonMasSeleccionado:Boolean=false
    var ant = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        //Cerrar teclado que se abre automaticamente
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        setContentView(binding.root)

        createFragment(ListarFragment())
        lstFragments.add(R.id.botonInicio)
        //Hacer que las notificacionmes de la barra de navagacion sea visible
        binding.bottomNavigation.getOrCreateBadge(R.id.botonGusta).isVisible=true
        //Dar notificaciones a los icones de la barra de navegacion
        binding.bottomNavigation.getOrCreateBadge(R.id.botonGusta).number=2



        var tuFragment = TuFragment()
        tuFragment.setArguments(bundle)

        binding.bottomNavigation.setOnItemSelectedListener { item -> when(item.itemId) {

                R.id.botonInicio -> {
                    binding.bottomNavigation.getOrCreateBadge(R.id.botonGusta).isVisible=false
                    // An icon only badge will be displayed unless a number is set:
                    println("ANT: "+ant)
                    if(item.itemId!=ant){
                        createFragment(ListarFragment())
                        lstFragments.add(R.id.botonInicio)
                    }
                    ant = R.id.botonInicio
                    true
                }

                R.id.botonGusta -> {
                    if(item.itemId!=ant){
                        createFragment(LikeFragment())
                        lstFragments.add(R.id.botonGusta)
                    }
                    ant = R.id.botonGusta
                    true
                }
                R.id.botonMas -> {
                    if(item.itemId!=ant){
                        createFragment(ListarFragment())
                        lstFragments.add(R.id.botonInicio)
                        var intent = Intent(applicationContext, SeleccionarCategoria::class.java)
                       startActivity(intent)
                    }

                    true
                }
            R.id.botonPerfil -> {
                binding.bottomNavigation.getOrCreateBadge(R.id.botonPerfil).isVisible=false
                // An icon only badge will be displayed unless a number is set:
                if(item.itemId!=ant){

                    createFragment(tuFragment)
                    lstFragments.add(R.id.botonPerfil)
                }
                ant = R.id.botonPerfil
                true
            }
                else -> false
            }
        }

        binding.activityPrincipal.setOnClickListener(){
            hideSoftkeyboard(binding.activityPrincipal)
        }

        recuperarUsuario()



    }



    override fun onBackPressed() {
        super.onBackPressed()
        //Para pasar por la pantalla home al final
        if(lstFragments.isNotEmpty()){
            lstFragments.removeLast()
            binding.bottomNavigation.menu.findItem(lstFragments.last()).setChecked(true)
        }

    }

    fun createFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(binding.FrameLayoutPrincipal.id, fragment)
            addToBackStack(null)

        }.commit()
}

    fun hideSoftkeyboard(vista: View){
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(vista.windowToken,0)
    }

fun recuperarUsuario(){
    val dbSh = this.getSharedPreferences("dataUser", Context.MODE_PRIVATE)
    var id = dbSh.getString("id_User", "")
    CoroutineScope(Dispatchers.Main).launch {
        // access = UsuarioLogica().LoginUser(binding.txtEmail.text.toString(),binding.txtPassword.text.toString())
        oneUser = UsuarioLogica().getOneUser(id.toString())
        bundle.putString("message", oneUser.nombre + " "  + oneUser.apellido)
        val jsonStringUsuario = Json.encodeToString(oneUser)
        bundle.putSerializable("usuario",jsonStringUsuario)
    }
}

    fun suma(x: Int, y:Int, su:(Int,Int)->Int){
        su(x,y)
    }

    override fun onKeyDown (keyCode: Int, event: KeyEvent?): Boolean {

        if(lstFragments.size==1) {


            // TODO Auto-generated method stub
            if (keyCode == event?.keyCode) {
                var builder = AlertDialog.Builder(this)
                builder.setTitle("Alerta")
                builder.setMessage("¿Desea salir de EcuChange?")
                builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                    Toast.makeText(
                        applicationContext,
                        android.R.string.yes, Toast.LENGTH_SHORT
                    ).show()
                    var intent = Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                builder.setNegativeButton(android.R.string.no) { dialog, which ->
                    Toast.makeText(
                        applicationContext,
                        android.R.string.no, Toast.LENGTH_SHORT
                    ).show()
                }
                builder.show()
            }
        }
        return super.onKeyDown(keyCode, event)

    }


    }


