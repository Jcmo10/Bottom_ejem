package com.jcmo.bottom_ejem


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class InventarioFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_inventario,container,false)

        var peluchito = arguments?.getParcelableArrayList<Peluches>("peluchess")
        peluchito?.toMutableList()
        var peluchitos : MutableList<Peluches> = ArrayList()
        peluchitos = peluchito!!

        recyclerView  = root.findViewById(R.id.recycler)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this.context,RecyclerView.VERTICAL,false) 

        val pelucheAdapter = PeluchesAdapter(peluchito,this.requireContext())
        recyclerView.adapter = pelucheAdapter

        return root

    }


}
