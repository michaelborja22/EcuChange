package com.example.ecuchange.entities

data class Usuario (val id: Int=2, val nombre: String) {

var direccion : String =""

    constructor(id: Int, nombre: String, apellido : String) : this (id,nombre)

}