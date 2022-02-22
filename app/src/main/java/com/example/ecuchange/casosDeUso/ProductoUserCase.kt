package com.example.ecuchange.casosDeUso
import com.example.ecuchange.data.api.RetrofitAPI
import com.example.ecuchange.data.api.entidades.toArticlesEntity
import com.example.ecuchange.data.api.service.ArticulosService
import com.example.ecuchange.entities.Products
import retrofit2.create

class ProductoUserCase{

private val productosList = listOf<Products>(
    Products("1",
        "Laptop Acer 7600",
        "Ram 8gb Color blanca",
        "https://1.bp.blogspot.com/--c3KYdSz9Sg/YUlMsHlvW1I/AAAAAAAAVVo/n8YPk9NzUYYBSj4kBHbOV4Mw9vjmS7GtQCLcBGAsYHQ/s198/66591687-vector-logo-plana-de-taxi-aislado-sobre-fondo-blanco-la-cara-del-coche-icono-de-la-silueta-modelo-de.jpg"
    ),
    Products("2",
        "Xbox Series x",
        "La ultima consola de videojuegos",
        "https://1.bp.blogspot.com/-9hfiTS1GRGw/YUjqpH5LX1I/AAAAAAAAVVE/TBLYr0reFbMlF079vOMZltrJ6txLAyBiwCLcBGAsYHQ/s64/taxi.png",
    ),
    Products("3",
        "Motoneta Gp Zuzuki",
        "100 caballos de fuerza Color blanca",
        "http://a.fsdn.com/sd/topics/graphics_64.png"
    )


    )

suspend fun getAllProducts() : List<Products>{

    var resp: MutableList<Products> = ArrayList<Products>()
    val service = RetrofitAPI.getArticulosApi().create(ArticulosService::class.java)
    val call = service.getAllArticulosbyCategoria("articulos")

    resp = if (call.isSuccessful) {
        val body = call.body()
        body!!.articles.map {
            it.toArticlesEntity()
        } as MutableList<Products>

    }else {
        ArrayList<Products>()

    }
    return resp
}

}