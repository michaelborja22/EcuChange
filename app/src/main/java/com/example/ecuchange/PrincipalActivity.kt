package com.example.ecuchange

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ecuchange.databinding.ActivityPrincipalBinding
import androidx.fragment.app.Fragment as Fragment

class PrincipalActivity : AppCompatActivity() {

    private lateinit var binding: com.example.ecuchange.databinding.ActivityPrincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.bottomNavigation.setOnClickListener(){

        }

        //Hacer que las notificacionmes de la barra de navagacion sea visible
        binding.bottomNavigation.getOrCreateBadge(R.id.botonGusta).isVisible=true
        //Dar notificaciones a los icones de la barra de navegacion
        binding.bottomNavigation.getOrCreateBadge(R.id.botonGusta).number=2

        binding.bottomNavigation.setOnItemSelectedListener { item -> when(item.itemId) {

                R.id.botonInicio -> {
                    binding.bottomNavigation.getOrCreateBadge(R.id.botonGusta).isVisible=false
                    // An icon only badge will be displayed unless a number is set:
                    createFragment(HomeFragment())
                    true
                }

                R.id.botonGusta -> {
                    createFragment(LikeFragment())
                    true
                }
                R.id.botonChat -> {
                    createFragment(ChatFragment())
                    true
                }
                else -> false
            }
        }


    }

    fun createFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.FrameLayout.id,fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
}

    }


