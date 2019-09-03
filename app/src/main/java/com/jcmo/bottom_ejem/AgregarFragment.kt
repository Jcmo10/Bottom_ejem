package com.jcmo.bottom_ejem


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_agregar.view.*
import java.lang.ClassCastException


class AgregarFragment : Fragment() {


    var interfaz : comunicador ?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_agregar, container, false)
        root.bEnviar.setOnClickListener{
            var id = root.eid.text.toString()
            var nom = root.eNombre.text.toString()
            var pre = root.ePrecio.text.toString()
            var cant = root.eCantidad.text.toString()
            if(id.isEmpty() || nom.isEmpty() || pre.isEmpty() || cant.isEmpty()){
                Toast.makeText(context, "Campos Vacios", Toast.LENGTH_SHORT).show()
            }else{
                interfaz?.agregarDatos(id,nom,pre,cant)
                //root.eid.text = R.string.Precio
            }

        }
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
