package com.example.ecuchange.logica

import com.example.ecuchange.casosDeUso.UsuarioUserCase
import com.example.ecuchange.data.database.entidades.UsuarioEntity
import com.example.ecuchange.entities.Usuario

class UsuarioLogica {

    suspend fun LoginUser( nombre:String,password:String):UsuarioEntity{

        /*
        print("\nUsuario: "+nombre.trim().uppercase()+" \n")
        print("Usuario: "+password+"\n \n")
        */
        var us = UsuarioUserCase().getUser(nombre,password)
        return us
        // return(us.id=="-1L")
    }

    suspend fun getOneUser( id:String): UsuarioEntity{
        var us = UsuarioUserCase().getOneUser(id)
        return us
    }

}