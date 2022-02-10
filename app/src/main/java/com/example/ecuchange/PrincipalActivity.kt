package com.example.ecuchange

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ecuchange.databinding.ActivityPrincipalBinding
import androidx.fragment.app.Fragment as Fragment

class PrincipalActivity : AppCompatActivity() {

    private lateinit var binding: com.example.ecuchange.databinding.ActivityPrincipalBinding
private var listaFragments = mutableListOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        createFragment(HomeFragment())
        setContentView(binding.root)

        //Hacer que las notificacionmes de la barra de navagacion sea visible
        binding.bottomNavigation.getOrCreateBadge(R.id.botonGusta).isVisible=true
        //Dar notificaciones a los icones de la barra de navegacion
        binding.bottomNavigation.getOrCreateBadge(R.id.botonGusta).number=2

        //Empiezo la activity con el fragment home


        binding.bottomNavigation.setOnItemSelectedListener { item -> when(item.itemId) {

                R.id.botonInicio -> {
                    binding.bottomNavigation.getOrCreateBadge(R.id.botonGusta).isVisible=false
                    // An icon only badge will be displayed unless a number is set:
                    listaFragments.add(item.itemId)
                    createFragment(HomeFragment())
                    true
                }

                R.id.botonGusta -> {
                    listaFragments.add(item.itemId)
                    createFragment(LikeFragment())
                    true
                }
                R.id.botonChat -> {
                    createFragment(ChatFragment())
                    listaFragments.add(item.itemId)
                    true
                }
                else -> false
            }
        }


    }

    override fun onBackPressed() {
        super.onBackPressed()
        if(listaFragments.isNotEmpty()) {
            listaFragments.removeLast()
            binding.bottomNavigation.menu.findItem(listaFragments.last()).setChecked(true)
        }
    }

    fun createFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
        replace(binding.FrameLayout.id,fragment)
        addToBackStack(null)
        commit()
        }

    }

    }


