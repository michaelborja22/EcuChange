package com.example.ecuchange.casosDeUso

import com.example.ecuchange.entities.Usuario

class ManejadorUsuario {

    fun ingresarUsuario(){
        var usuario: Usuario = Usuario(1,"Michael")
        usuario.direccion="Puembo"

        var usuario2: Usuario = Usuario(1,"Diego","Lopez")
    }


    fun actualizarUsuario(){

    }

    fun borrarUsuario(){

    }

    fun listarUsuarios() : List<Usuario>{
        return listOf()
    }



}