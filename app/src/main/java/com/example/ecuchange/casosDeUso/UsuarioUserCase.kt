package com.example.ecuchange.casosDeUso

import com.example.ecuchange.entities.Usuario

class UsuarioUserCase {

    private val usuariosDB = listOf<Usuario>(
        Usuario("DEIVID","deivid"),
        Usuario("JOSE","jose"),
        Usuario("MANUEL","manuel"),
        Usuario("KAT","kat") ,
        Usuario("ALEX","alex"),
        Usuario("MICHAEL","michael")
    )


    //al final :Usuario significa que se va a retornar un objetoi de tipo usuario
    fun getUser(nombre:String,password:String) :Usuario{
        var us=Usuario()

        usuariosDB.forEach(){
            println(it)
            if(it.nombre==nombre && it.password==password){
                us=it
            }
        }
        return us
    }

    fun ingresarUsuario(){

    }


    fun actualizarUsuario(){

    }

    fun borrarUsuario(){

    }

    fun listarUsuarios() : List<Usuario>{
        return listOf()
    }




}