package com.example.ecuchange.entities

import java.util.*

data class Usuario (var id: String="", var nombre: String="", var password:String="") {

    var direccion: String = ""


    constructor(nombre: String, password: String) : this (){
        this.nombre=nombre
        this.password=password
        this.id=UUID.randomUUID().toString()
    }
}