package com.example.ecuchange.presentacion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ecuchange.R
import com.example.ecuchange.databinding.ActivityPrincipalBinding
import androidx.fragment.app.Fragment as Fragment

class PrincipalActivity : AppCompatActivity() {

    private lateinit var binding: com.example.ecuchange.databinding.ActivityPrincipalBinding
    private var lstFragments = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalBinding.inflate(layoutInflater)

        setContentView(binding.root)

        createFragment(HomeFragment())

        //Hacer que las notificacionmes de la barra de navagacion sea visible
        binding.bottomNavigation.getOrCreateBadge(R.id.botonGusta).isVisible=true
        //Dar notificaciones a los icones de la barra de navegacion
        binding.bottomNavigation.getOrCreateBadge(R.id.botonGusta).number=2

        var ant = 0

        binding.bottomNavigation.setOnItemSelectedListener { item -> when(item.itemId) {

                R.id.botonInicio -> {
                    binding.bottomNavigation.getOrCreateBadge(R.id.botonGusta).isVisible=false
                    // An icon only badge will be displayed unless a number is set:

                    if(item.itemId!=ant){
                        createFragment(HomeFragment())
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
                R.id.botonChat -> {

                    if(item.itemId!=ant){
                        lstFragments.add(R.id.botonChat)
                        createFragment(ChatFragment())
                    }
                    ant = R.id.botonChat
                    true
                }
                else -> false
            }
        }


    }

    override fun onBackPressed() {
        super.onBackPressed()
        //Para pasar por la pantalla home al final
        var final=0
        if(lstFragments.isNotEmpty()){
            lstFragments.removeLast()
            binding.bottomNavigation.menu.findItem(lstFragments.last()).setChecked(true)
        }

    }

    fun createFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(binding.FrameLayout.id, fragment)
            addToBackStack(null)
            commit()
        }
}



    }


