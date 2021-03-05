package com.example.companiasapp


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

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
        val owner = Owner("")
        val arrayList: ArrayList<CompaniasData> = ArrayList()
        arrayList.add(CompaniasData("CLARO", "Tiempo aire", owner))
        arrayList.add(CompaniasData("CLARO", "Megas", owner))
        arrayList.add(CompaniasData("CLARO", "Megas", owner))
        val recyclerList = CompaniasList(arrayList)
        companiasListData.value = recyclerList
    }

    fun getListDataEntel() {
        val owner = Owner("")
        val arrayList: ArrayList<CompaniasData> = ArrayList()
        arrayList.add(CompaniasData("ENTEL", "Tiempo Aire", owner))
        arrayList.add(CompaniasData("ENTEL", "Tiempo Aire", owner))
        arrayList.add(CompaniasData("ENTEL", "Tiempo Aire", owner))
        arrayList.add(CompaniasData("ENTEL", "Megas", owner))
        val recyclerList = CompaniasList(arrayList)
        companiasListDataEntel.value = recyclerList
    }

    fun getListDataTuenti() {
        val owner = Owner("")
        val arrayList: ArrayList<CompaniasData> = ArrayList()
        arrayList.add(CompaniasData("ENTEL", "Megas", owner))
        arrayList.add(CompaniasData("ENTEL", "Megas", owner))
        arrayList.add(CompaniasData("ENTEL", "Tiempo Aire", owner))
        arrayList.add(CompaniasData("ENTEL", "Megas", owner))
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