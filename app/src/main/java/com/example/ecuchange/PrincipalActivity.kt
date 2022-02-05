package com.example.ecuchange

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ecuchange.databinding.ActivityPrincipalBinding

class PrincipalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPrincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.bottomNavigation.setOnClickListener(){
            binding.textView9.text="DA"
        }


    }


}