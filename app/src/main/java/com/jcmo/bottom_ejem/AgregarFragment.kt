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
import kotlinx.android.synthetic.main.fragment_agregar.view.*
import java.lang.ClassCastException


class AgregarFragment : Fragment() {


    var interfaz : comunicador ?= null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var bolean : Boolean = true

        var peluchito = arguments?.getParcelableArrayList<Peluches>("peluchess")
        peluchito?.toMutableList()

        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_agregar, container, false)
        root.bEnviar.setOnClickListener{
            var ids = root.eid.text.toString()
            var nom = root.eNombre.text.toString()
            var pre = root.ePrecio.text.toString()
            var cant = root.eCantidad.text.toString()
            if(ids.isEmpty() || nom.isEmpty() || pre.isEmpty() || cant.isEmpty()){
                Toast.makeText(context, "Campos Vacios", Toast.LENGTH_SHORT).show()
            }else{
                    //var sise = peluchito?.size
               /* if (peluchito != null) {
                    for(i in peluchito) {
                        if (i.id.equals(ids) ) {
                            bolean = true
                        }

                    }
                }else{
                    Toast.makeText(context, "Campos Vacios", Toast.LENGTH_SHORT).show()
                }*/

                //if (bolean){
                    val builder = AlertDialog.Builder(this.context)
                    builder.setTitle("CONFIRMAR")
                    builder.setMessage("ID: $ids \nNombre: $nom \nPrecio: $pre \nCantidad $cant \n")
                    //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

                    builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                        Toast.makeText(context,"Agregado", Toast.LENGTH_SHORT).show()
                        interfaz?.agregarDatos(ids,nom,pre,cant)
                        root.eid.text.clear()
                        root.eNombre.text.clear()
                        root.ePrecio.text.clear()
                        root.eCantidad.text.clear()
                    }
                    builder.setNegativeButton(android.R.string.no) { dialog, which ->
                        Toast.makeText(context,"No Agregado", Toast.LENGTH_SHORT).show()
                    }
                    builder.show()
               /* }else{
                    Toast.makeText(context, "Ya Existente", Toast.LENGTH_SHORT).show()
                }*/


                /*interfaz?.agregarDatos(id,nom,pre,cant)
                root.eid.text.clear()
                root.eNombre.text.clear()
                root.ePrecio.text.clear()
                root.eCantidad.text.clear()*/

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
