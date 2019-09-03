package com.jcmo.bottom_ejem

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class MainActivity : AppCompatActivity(),comunicador {

    var peluchito : MutableList<Peluches> = ArrayList()
    private lateinit var textMessage: TextView
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()

        when (item.itemId) {
            R.id.navigation_agregar -> {
                val agregarFragment = AgregarFragment()
                transaction.replace(R.id.contenedor, agregarFragment).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_buscar -> {
                val buscarFragment = BuscarFragment()
                transaction.replace(R.id.contenedor, buscarFragment).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_eliminar -> {
                val eliminarFragment = EliminarFragment()
                transaction.replace(R.id.contenedor, eliminarFragment).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_inventario -> {
                val inventarioFragment = InventarioFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("peluchess", ArrayList<Peluches>(peluchito))
                inventarioFragment.arguments = bundle
                transaction.replace(R.id.contenedor,inventarioFragment).commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val navView: BottomNavigationView = findViewById(R.id.nav_view2)
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        val agregarFragment = AgregarFragment()

        peluchito.add(Peluches("001","Oso","$1000","100"))
        peluchito.add(Peluches("001","Tigre","$1000","1"))
        peluchito.add(Peluches("001","Leon","$100","10"))
        peluchito.add(Peluches("001","Abeja","$10","8"))


        transaction.add(R.id.contenedor, agregarFragment).commit()
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    override fun agregarDatos(id: String, nombre: String, precio: String, cantidad: String) {
        //val args = Bundle()

        var peluchi = Peluches(id,nombre,precio,cantidad)
        peluchito.add(peluchi)

    }

}
