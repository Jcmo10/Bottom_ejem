package com.jcmo.bottom_ejem


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.peluchito_item.view.*

class EliminarAdapter: RecyclerView.Adapter<EliminarAdapter.EliminarViewHolder> {
    private var listPeluches: List<Peluches>? = null
    private var context : Context? = null

    constructor(listPeluches: List<Peluches>,context: Context){
        this.listPeluches = listPeluches
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EliminarViewHolder {



        var view = LayoutInflater.from(context).inflate(R.layout.peluchedeleteitem,parent,false)

        return EliminarAdapter.EliminarViewHolder(view)

    }

    override fun getItemCount(): Int {
        return listPeluches?.size!!
    }

    override fun onBindViewHolder(holder: EliminarViewHolder, position: Int) {
        var peluches = listPeluches!![position]
        holder.loadItem(peluches)
    }


    class EliminarViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun loadItem(peluche: Peluches){
            itemView.tid.text = peluche.id

            itemView.tNombre.text = peluche.nombre

            itemView.tPrecio.text = peluche.precio

            itemView.tStock.text = peluche.cantidad
        }

    }



}