package com.jcmo.bottom_ejem

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_buscar.*

class MainActivity : AppCompatActivity(),comunicador {

    var peluchito : MutableList<Peluches> = ArrayList()
    var peluchitos : MutableList<Peluches> = ArrayList()
    private lateinit var textMessage: TextView


    //val search = findViewById<EditText>(R.id.eBuscar)

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()


        when (item.itemId) {
            R.id.navigation_agregar -> {
                val agregarFragment = AgregarFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("peluchess", ArrayList<Peluches>(peluchito))
                agregarFragment.arguments = bundle
                transaction.replace(R.id.contenedor, agregarFragment).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_buscar -> {
                val buscarFragment = BuscarFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("peluchess", ArrayList<Peluches>(peluchito))
                buscarFragment.arguments = bundle
                transaction.replace(R.id.contenedor,buscarFragment).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_eliminar -> {
                val eliminarFragment = EliminarFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("peluchess", ArrayList<Peluches>(peluchito))
                eliminarFragment.arguments = bundle
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


        //peluchitos = peluchito
        //peluchitos.toList()




        transaction.replace(R.id.contenedor, agregarFragment).commit()
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    override fun agregarDatos(id: String, nombre: String, precio: String, cantidad: String) {
        //val args = Bundle()

        var peluchi = Peluches(id,nombre, "$$precio",cantidad)
        peluchito.add(peluchi)

    }

    override fun Buscar(nombre: String){
        peluchitos.clear()
        var bol = false
        for(i in peluchito){
            if(i.nombre!!.toLowerCase().contains(nombre.toLowerCase())){
                peluchitos.add(Peluches(i.id,i.nombre,i.cantidad,i.precio))
                bol = true
            }
            val manager = supportFragmentManager
            val transaction = manager.beginTransaction()
            val buscarFragment = BuscarFragment()
            val bundle = Bundle()

            if(bol){
                bundle.putParcelableArrayList("peluchess", ArrayList<Peluches>(peluchitos))
                bundle.putString("key","true")
                //
            }else{
                bundle.putParcelableArrayList("peluchess", ArrayList<Peluches>(peluchito))
                bundle.putString("key","false")
            }

            buscarFragment.arguments = bundle
            transaction.replace(R.id.contenedor,buscarFragment).commit()
        }
    }

    override fun eliminarDato(nombres: String){//,bool :Boolean) {
        peluchitos = peluchito
        //var bol = bool

        /*for (i in peluchito){
            if(i.nombre!!.toLowerCase().equals(nombres.toLowerCase())){

                //var ayuda = peluchito.indexOf(i)
                //peluchitos.removeAt(ayuda)
                bol = true
            }
        }*/
        peluchito.removeAll{it.nombre?.toLowerCase() == nombres.toLowerCase()}
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        val eliminarFragment = EliminarFragment()
        val bundle = Bundle()
        bundle.putParcelableArrayList("peluchess", ArrayList<Peluches>(peluchito))

        /*if(bol){
            //bundle.putParcelableArrayList("peluchess", ArrayList<Peluches>(peluchito))
            bundle.putString("key","true")
            //
        }else{
            //bundle.putParcelableArrayList("peluchess", ArrayList<Peluches>(peluchito))
            bundle.putString("key","false")
        }*/
        eliminarFragment.arguments = bundle
        transaction.replace(R.id.contenedor,eliminarFragment).commit()


       }


}
