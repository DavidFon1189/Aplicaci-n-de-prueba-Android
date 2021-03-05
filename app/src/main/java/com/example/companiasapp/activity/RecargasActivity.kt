package com.example.companiasapp.activity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.companiasapp.R
import com.example.companiasapp.CompaniasList
import com.example.companiasapp.CompaniasViewModel
import com.example.companiasapp.ServiciosList
import com.example.companiasapp.adapter.AdapterClaro
import com.example.companiasapp.adapter.AdapterEntel
import com.example.companiasapp.adapter.AdapterServicios
import com.example.companiasapp.adapter.AdapterTuenti
import kotlinx.android.synthetic.main.recargas_activity.*

class RecargasActivity: AppCompatActivity() {
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recargas_activity)
        val companiasViewModel = ViewModelProvider(this).get(CompaniasViewModel::class.java)

        recycler_view_servicios.setHasFixedSize(true)
        val layoutManagerServicios = LinearLayoutManager(this)
        layoutManagerServicios.orientation = LinearLayoutManager.HORIZONTAL
        recycler_view_servicios.layoutManager = layoutManagerServicios
        recycler_view_servicios.itemAnimator = DefaultItemAnimator()
        // get data servicios
        var adapterServicios: AdapterServicios?
        companiasViewModel.getListDataServicios()
        companiasViewModel.getRecyclerListDataObserverServicios().observe(this, Observer<ServiciosList>{
            adapterServicios = AdapterServicios(it.items)
            recycler_view_servicios.adapter = adapterServicios
        })

        //Recyclerview Claro
        recycler_view_claro.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recycler_view_claro.layoutManager = layoutManager
        recycler_view_claro.itemAnimator = DefaultItemAnimator()
        //get data claro
        var adapterClaro: AdapterClaro?
        companiasViewModel.getListData()
        companiasViewModel.getRecyclerListDataObserver().observe(this, Observer<CompaniasList>{
             adapterClaro = AdapterClaro(it.items)
            recycler_view_claro.adapter = adapterClaro
        })

        //Recycler view Tuenti
        recycler_view_tuenti.setHasFixedSize(true)
        val layoutManagerTuenti = LinearLayoutManager(this)
        layoutManagerTuenti.orientation = LinearLayoutManager.HORIZONTAL
        recycler_view_tuenti.layoutManager = layoutManagerTuenti
        recycler_view_tuenti.itemAnimator = DefaultItemAnimator()
        var adapterTuenti: AdapterTuenti?
        companiasViewModel.getListDataTuenti()
        companiasViewModel.getRecyclerListDataObserverTuenti().observe(this, Observer<CompaniasList>{
            adapterTuenti = AdapterTuenti(it.items)
            recycler_view_tuenti.adapter = adapterTuenti
        })

        //Recycler view Entel
        recycler_view_entel.setHasFixedSize(true)
        val layoutManagerEntel = LinearLayoutManager(this)
        layoutManagerEntel.orientation = LinearLayoutManager.HORIZONTAL
        recycler_view_entel.layoutManager = layoutManagerEntel
        recycler_view_entel.itemAnimator = DefaultItemAnimator()
        var adapterEntel: AdapterEntel?
        companiasViewModel.getListDataEntel()
        companiasViewModel.getRecyclerListDataObserverEntel().observe(this, Observer<CompaniasList>{
            adapterEntel = AdapterEntel(it.items)
            recycler_view_entel.adapter = adapterEntel
        })

//        preferences = getSharedPreferences("SHARED_PREFERENCES", Context.MODE_PRIVATE)
//        Toast.makeText(this, preferences.getString("access_token", ""), Toast.LENGTH_SHORT).show()
    }


}