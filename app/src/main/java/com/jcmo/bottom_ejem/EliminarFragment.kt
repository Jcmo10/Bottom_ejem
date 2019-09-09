package com.jcmo.bottom_ejem


import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_agregar.view.*
import kotlinx.android.synthetic.main.fragment_buscar.view.*
import kotlinx.android.synthetic.main.fragment_eliminar.view.*
import kotlinx.android.synthetic.main.peluchedeleteitem.view.*
import java.lang.ClassCastException


class EliminarFragment : Fragment() {

    private var interfaz : comunicador ?= null

    private var peluchito: MutableList<Peluches>? = ArrayList()
    private var peluchitos: MutableList<Peluches>? = ArrayList()

    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {


        var key = arguments?.getString("key")
        var eliminarAdapter : EliminarAdapter
        val root = inflater.inflate(R.layout.fragment_eliminar,container,false)


        peluchito = arguments?.getParcelableArrayList<Peluches>("peluchess")
        root.bEliminar.setOnClickListener{
            var los = false
            peluchito = arguments?.getParcelableArrayList<Peluches>("peluchess")
            var names = root.eBuscar2.text.toString()
            if (names.isEmpty()){
                Toast.makeText(this.context,"Campo Vacio",Toast.LENGTH_SHORT).show()
            }else{
                var help: Peluches? = null

                loop@for (i in peluchito!!){
                    if(i.nombre!!.toLowerCase().equals( names.toLowerCase())){
                        los = true
                        help = i
                        break@loop
                    }
                }
                if (los){
                    val builder = AlertDialog.Builder(this.context)
                    builder.setTitle("CONFIRMAR")
                    builder.setMessage("ID: ${help?.id} \nNombre: ${help?.nombre} \nPrecio: ${help?.precio} \nCantidad ${help?.cantidad} \n")

                    builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                        //Toast.makeText(context,"Agregado", Toast.LENGTH_SHORT).show()
                        Toast.makeText(this.context,"Elemento Elimindao",Toast.LENGTH_SHORT).show()
                        interfaz?.eliminarDato(names)
                    }
                    builder.setNegativeButton(android.R.string.no) { dialog, which ->
                        Toast.makeText(context,"Cancelado", Toast.LENGTH_SHORT).show()
                        //interfaz?.eliminarDato("")
                        //interfaz?.eliminarDato(names,false)
                    }
                    builder.show()
                }else{
                    interfaz?.eliminarDato(names)
                    Toast.makeText(this.context,"Elemento no encontrado",Toast.LENGTH_SHORT).show()
                }

            }
        }


        //peluchito = arguments?.getParcelableArrayList<Peluches>("peluchess")
        recyclerView  = root.findViewById(R.id.recycler2)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL,false)


        /*if(key.equals("true")){

            //peluchito = arguments?.getParcelableArrayList<Peluches>("peluche")
            //eliminarAdapter = context?.let { EliminarAdapter(peluchitos!!, it) }!!
            Toast.makeText(this.context,"Elemento Eliminado",Toast.LENGTH_SHORT).show()


        }else{
            //peluchito = arguments?.getParcelableArrayList<Peluches>("peluchess")
            //eliminarAdapter = context?.let { EliminarAdapter(peluchito!!, it) }!!
            Toast.makeText(this.context,"Elemento no encontrado",Toast.LENGTH_SHORT).show()
        }*/

         eliminarAdapter = EliminarAdapter(peluchito!!,this.requireContext())
        recyclerView.adapter = eliminarAdapter


        return root
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            interfaz = context as comunicador
        }catch (e: ClassCastException){
            Log.d("exception", e.toString())
        }
    }

}
