package com.example.companiasapp.fragment

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.companiasapp.ClickRecycler
import com.example.companiasapp.R
import com.example.companiasapp.model.CompaniasList
import com.example.companiasapp.viewmodel.CompaniasViewModel
import com.example.companiasapp.model.ServiciosList
import com.example.companiasapp.activity.MontoRecargaActivity
import com.example.companiasapp.adapter.AdapterClaro
import com.example.companiasapp.adapter.AdapterEntel
import com.example.companiasapp.adapter.AdapterServicios
import com.example.companiasapp.adapter.AdapterTuenti
import com.example.companiasapp.model.CompaniasData
import com.example.companiasapp.utils.SaveData

class RecargasFragment: Fragment(), ClickRecycler {
    private lateinit var preferences: SharedPreferences
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.recargas_fragment, container, false)
        val companiasViewModel = ViewModelProvider(this).get(CompaniasViewModel::class.java)
        val recyclerViewServicios = view.findViewById<RecyclerView>(R.id.recycler_view_servicios)
        val recyclerViewClaro = view.findViewById<RecyclerView>(R.id.recycler_view_claro)
        val recyclerViewTuenti = view.findViewById<RecyclerView>(R.id.recycler_view_tuenti)
        val recyclerViewEntel= view.findViewById<RecyclerView>(R.id.recycler_view_entel)
        recyclerViewServicios?.setHasFixedSize(true)
        val layoutManagerServicios = LinearLayoutManager(activity)
        layoutManagerServicios.orientation = LinearLayoutManager.HORIZONTAL
        recyclerViewServicios?.layoutManager = layoutManagerServicios
        recyclerViewServicios?.itemAnimator = DefaultItemAnimator()
        // get data servicios
        var adapterServicios: AdapterServicios?
        companiasViewModel.getListDataServicios()
        activity?.let { it ->
            companiasViewModel.getRecyclerListDataObserverServicios().observe(it, Observer<ServiciosList>{
                adapterServicios = AdapterServicios(it.items)
                recyclerViewServicios?.adapter = adapterServicios
            })
        }

        //Recyclerview Claro
        recyclerViewClaro?.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerViewClaro?.layoutManager = layoutManager
        recyclerViewClaro?.itemAnimator = DefaultItemAnimator()
        //get data claro
        var adapterClaro: AdapterClaro?
        companiasViewModel.getListData()
        activity?.let {
            companiasViewModel.getRecyclerListDataObserver().observe(it, Observer<CompaniasList>{
                adapterClaro = AdapterClaro(it.items, this)
                recyclerViewClaro?.adapter = adapterClaro
                it.items.forEach { element ->
                    val db = context?.let { data -> SaveData(data) }
                    val companiasData = CompaniasData(element.compania, element.description, element.img)
                    db?.insertData(companiasData)

                }
            })
        }

        //Recycler view Tuenti
        recyclerViewTuenti?.setHasFixedSize(true)
        val layoutManagerTuenti = LinearLayoutManager(activity)
        layoutManagerTuenti.orientation = LinearLayoutManager.HORIZONTAL
        recyclerViewTuenti?.layoutManager = layoutManagerTuenti
        recyclerViewTuenti?.itemAnimator = DefaultItemAnimator()
        var adapterTuenti: AdapterTuenti?
        companiasViewModel.getListDataTuenti()
        activity?.let {
            companiasViewModel.getRecyclerListDataObserverTuenti().observe(it, Observer<CompaniasList>{
                adapterTuenti = AdapterTuenti(it.items, this)
                recyclerViewTuenti?.adapter = adapterTuenti
            })
        }

        //Recycler view Entel
        recyclerViewEntel?.setHasFixedSize(true)
        val layoutManagerEntel = LinearLayoutManager(activity)
        layoutManagerEntel.orientation = LinearLayoutManager.HORIZONTAL
        recyclerViewEntel?.layoutManager = layoutManagerEntel
        recyclerViewEntel?.itemAnimator = DefaultItemAnimator()
        var adapterEntel: AdapterEntel?
        companiasViewModel.getListDataEntel()
        activity?.let {
            companiasViewModel.getRecyclerListDataObserverEntel().observe(it, Observer<CompaniasList>{
                adapterEntel = AdapterEntel(it.items, this)
                recyclerViewEntel?.adapter = adapterEntel
            })
        }

//        preferences = getSharedPreferences("SHARED_PREFERENCES", Context.MODE_PRIVATE)
//        Toast.makeText(this, preferences.getString("access_token", ""), Toast.LENGTH_SHORT).show()
        return view
    }

    override fun onClickRecycler(compania: String, tipoRecarga: String) {
        val intent = Intent(activity, MontoRecargaActivity::class.java)
        intent.putExtra("compania", compania);
        intent.putExtra("tipoRecarga", tipoRecarga);
        startActivity(intent);
    }
}