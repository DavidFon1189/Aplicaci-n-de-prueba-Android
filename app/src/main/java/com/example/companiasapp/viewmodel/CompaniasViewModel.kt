package com.example.companiasapp.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.companiasapp.model.CompaniasData
import com.example.companiasapp.model.CompaniasList
import com.example.companiasapp.model.ServiciosData
import com.example.companiasapp.model.ServiciosList

class CompaniasViewModel: ViewModel() {

    var serviciosListData: MutableLiveData<ServiciosList> = MutableLiveData()
    var companiasListData: MutableLiveData<CompaniasList> = MutableLiveData()
    var companiasListDataEntel: MutableLiveData<CompaniasList> = MutableLiveData()
    var companiasListDataTuenti: MutableLiveData<CompaniasList> = MutableLiveData()

    fun getRecyclerListDataObserverServicios(): MutableLiveData<ServiciosList> {
        return serviciosListData
    }

    fun getRecyclerListDataObserver(): MutableLiveData<CompaniasList> {
        return companiasListData
    }

    fun getRecyclerListDataObserverEntel(): MutableLiveData<CompaniasList> {
        return companiasListDataEntel
    }

    fun getRecyclerListDataObserverTuenti(): MutableLiveData<CompaniasList> {
        return companiasListDataTuenti
    }

    fun getListData() {
        val arrayList: ArrayList<CompaniasData> = ArrayList()
        arrayList.add(CompaniasData("CLARO", "Tiempo Aire", "url"))
        arrayList.add(CompaniasData("CLARO", "Megas", "url"))
        arrayList.add(CompaniasData("CLARO", "Megas", "url"))
        val recyclerList = CompaniasList(arrayList)
        companiasListData.value = recyclerList
    }

    fun getListDataEntel() {
        val arrayList: ArrayList<CompaniasData> = ArrayList()
        arrayList.add(CompaniasData("ENTEL", "Tiempo Aire", "url"))
        arrayList.add(CompaniasData("ENTEL", "Tiempo Aire", "url"))
        arrayList.add(CompaniasData("ENTEL", "Tiempo Aire", "url"))
        arrayList.add(CompaniasData("ENTEL", "Megas", "url"))
        val recyclerList = CompaniasList(arrayList)
        companiasListDataEntel.value = recyclerList
    }

    fun getListDataTuenti() {
        val arrayList: ArrayList<CompaniasData> = ArrayList()
        arrayList.add(CompaniasData("ENTEL", "Megas", "url"))
        arrayList.add(CompaniasData("ENTEL", "Megas", "url"))
        arrayList.add(CompaniasData("ENTEL", "Tiempo Aire", "url"))
        arrayList.add(CompaniasData("ENTEL", "Megas", "url"))
        val recyclerList = CompaniasList(arrayList)
        companiasListDataTuenti.value = recyclerList
    }

    fun getListDataServicios() {
        val arrayList: ArrayList<ServiciosData> = ArrayList()
        arrayList.add(ServiciosData("RECARGAS"))
        arrayList.add(ServiciosData("ADMINISTRACIÓN"))
        arrayList.add(ServiciosData("RECAUDACIÓN"))
        val recyclerList = ServiciosList(arrayList)
        serviciosListData.value = recyclerList
    }
}