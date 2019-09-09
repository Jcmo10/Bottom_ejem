package com.jcmo.bottom_ejem

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.peluchito_item.view.*

class BuscarAdapter :RecyclerView.Adapter<BuscarAdapter.BuscarViewHolder>  {


    private var listPeluches: MutableList<Peluches>? = null
    private var listPeluchesfull: MutableList<Peluches>? = null

    private var context : Context? = null

    

    constructor(listPeluches: MutableList<Peluches>,context: Context){
        this.listPeluches = listPeluches
        listPeluchesfull = ArrayList<Peluches>(listPeluches)
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuscarAdapter.BuscarViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.peluchito_item,parent,false)
        return BuscarAdapter.BuscarViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listPeluches?.size!!
    }

    override fun onBindViewHolder(holder: BuscarViewHolder, position: Int) {
        val peluches = listPeluches!![position]
        holder.loadItem(peluches)
    }


    class BuscarViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun loadItem(peluche: Peluches){

            itemView.tid.text = peluche.id


            itemView.tNombre.text = peluche.nombre

            itemView.tPrecio.text = peluche.precio

            itemView.tStock.text = peluche.cantidad



        }
    }



}