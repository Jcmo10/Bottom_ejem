package com.jcmo.bottom_ejem

interface comunicador {
    fun agregarDatos(id:String, nombre: String, precio:String, cantidad:String)

    fun Buscar(nombre:String)

    fun eliminarDato(nombres:String)
}

