package com.example.ecuchange.presentacion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.adoptame.database.entidades.ArticlesEntity
import com.example.ecuchange.R
import com.example.ecuchange.databinding.ActivityItemBinding
import com.example.ecuchange.databinding.ActivityLoginBinding


class ItemActivity : AppCompatActivity() {

        private lateinit var binding: ActivityItemBinding

        private var fav: Boolean = false

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityItemBinding.inflate(layoutInflater)
            setContentView(binding.root)

            binding.textView7.text="BIENVENIDO"

            intent.extras?.let{
                var p = it.get("producto")

                print("\n PRODUCTO : "+p+"\n")
                val n : ArticlesEntity = p as ArticlesEntity
                if (n is ArticlesEntity){
                    print("si ESO\n")
                }else{
                    print("MENSAJE\n")
                }


            }

        }
}