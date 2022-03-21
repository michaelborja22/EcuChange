package com.example.ecuchange.casosDeUso

import com.example.adoptame.database.entidades.ArticlesEntity
import com.example.ecuchange.data.api.RetrofitAPI
import com.example.ecuchange.data.api.entidades.toArticlesEntity
import com.example.ecuchange.data.api.entidades.toOneUserEntity
import com.example.ecuchange.data.api.entidades.toUsuariosEntity
import com.example.ecuchange.data.api.service.ArticulosService
import com.example.ecuchange.data.api.service.UsuarioService
import com.example.ecuchange.data.database.entidades.UsuarioEntity
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
    suspend fun getUser(user:String,password:String) : UsuarioEntity{
        // var us=Usuario()

        // var resp: List<UsuarioEntity> = ArrayList<UsuarioEntity>()
        // = UsuarioEntity("0001","admin", "admin", "admin")
        var us: UsuarioEntity = UsuarioEntity("0001","admin", "admin", "admin")
        var respuesta: Boolean = false
        val service = RetrofitAPI.getUsuariosApi().create(UsuarioService::class.java)
        val call = service.getAllUsuarios("usuarios")
        println(call.body()!!.users)
        if (call.isSuccessful) {
            call.body()!!.users.forEach() {
                if (it.user == user && it.password == password) {
                    us = it.toUsuariosEntity()
                    respuesta = true
                }
            }
            println("en el if")
            println(us)

            return us
        } else {
            println("en el else")
            println(us)
            return us }
//        resp = if (call.isSuccessful) {
//            return call.body()!!.users.forEach() {
//                if (it.user == nombre && it.password == password) {
//                    it.toUsuariosEntity()
//                }
//            }
//        } else ()
//        return resp
//
//        usuariosDB.forEach(){
//            println(it)
//            if(it.nombre==nombre && it.password==password){
//                us=it
//            }
//        }
//        return us
    }

    suspend fun getOneUser (id: String) : UsuarioEntity {
        var us: UsuarioEntity = UsuarioEntity("0001","admin", "admin", "admin", "0999999999")
        val service = RetrofitAPI.getUsuariosApi().create(UsuarioService::class.java)
        val call = service.getOneUser("usuario/$id")

        if (call.isSuccessful) {
            us = call.body()!!.toOneUserEntity()
            println("en el if")
            println(us)

            return us
        } else {
            println("en el else")
            println(us)
            return us }
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