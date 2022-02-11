package com.example.ecuchange.logica

import com.example.ecuchange.casosDeUso.UsuarioUserCase
import com.example.ecuchange.entities.Usuario

class UsuarioLogica {

    fun LoginUser( nombre:String,password:String):Boolean{

        /*
        print("\nUsuario: "+nombre.trim().uppercase()+" \n")
        print("Usuario: "+password+"\n \n")
        */

        var us= UsuarioUserCase().getUser(nombre.trim().uppercase(),password)
        return(us.id=="-1L")
    }

}