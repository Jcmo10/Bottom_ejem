package com.jcmo.bottom_ejem


import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_buscar.view.*
import java.lang.ClassCastException


class BuscarFragment : Fragment() {
    var interfaz : comunicador ?= null

    private lateinit var recyclerView: RecyclerView
    private var peluchito: MutableList<Peluches>? = ArrayList()
    private var peluchitos: MutableList<Peluches>? = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_buscar,container,false)
        //val search = root.findViewById<EditText>(R.id.eBuscar)
        var key = arguments?.getString("key")
        var help : String = ""
        var buscarAdapter : BuscarAdapter

        peluchito = arguments?.getParcelableArrayList<Peluches>("peluchess")
        peluchitos = peluchito



        root.bBuscar.setOnClickListener{
            //peluchito?.clear()
            var name = root.eBuscar.text.toString()
            if (name.isEmpty()){
                Toast.makeText(this.context,"Campo Vacio",Toast.LENGTH_SHORT).show()
            }else{
                interfaz?.Buscar(name)
            }
        }



      // peluchitos = arguments?.getParcelableArrayList<Peluches>("peluche")
        //val buscarAdapter = context?.let { BuscarAdapter(peluchito!!, it) }
        //recyclerView.adapter = buscarAdapter
        //peluchitos = peluchito!!

        recyclerView  = root.findViewById(R.id.recycler)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)

       if(key.equals("true")){

            peluchitos = arguments?.getParcelableArrayList<Peluches>("peluchess")
            buscarAdapter = context?.let { BuscarAdapter(peluchitos!!, it) }!!


        }else{
           peluchito = arguments?.getParcelableArrayList<Peluches>("peluchess")
           buscarAdapter = context?.let { BuscarAdapter(peluchito!!, it) }!!
            //Toast.makeText(this.context,"Elemento no encontrado",Toast.LENGTH_SHORT).show()
        }



        recyclerView.adapter = buscarAdapter

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

