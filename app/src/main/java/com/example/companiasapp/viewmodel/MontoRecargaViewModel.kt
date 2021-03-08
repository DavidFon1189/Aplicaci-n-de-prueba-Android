package com.example.companiasapp.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.companiasapp.*
import com.example.companiasapp.model.MontoRecargaList

class MontoRecargaViewModel: ViewModel() {

    var telefono: String = ""
    var monto: String = ""

    var listenerInterfaceMontoRecarga: InterfaceListenerMontoRecarga? = null

    fun onClickButtonRecarga(view: View){
        if (telefono.isEmpty()){
            listenerInterfaceMontoRecarga?.error("Ingresa teléfono")
            return
        } else if (monto == "Selecciona Monto"){
            listenerInterfaceMontoRecarga?.error("Ingresa monto")
            return
        }

        listenerInterfaceMontoRecarga?.setDataMontoRecarga()
    }

    var montoRecargaList: MutableLiveData<MontoRecargaList> = MutableLiveData()
    var montoMegasList: MutableLiveData<MontoRecargaList> = MutableLiveData()

    fun getListDataObserverMontoRecarga(): MutableLiveData<MontoRecargaList> {
        return montoRecargaList
    }

    fun getListDataObserverMontoMegas(): MutableLiveData<MontoRecargaList> {
        return montoMegasList
    }

    fun getListMontoRecarga() {
        val arrayList: ArrayList<String> = ArrayList()
        arrayList.add("$20.00")
        arrayList.add("$50.00")
        arrayList.add("$100.00")
        val recyclerList = MontoRecargaList(arrayList)
        montoRecargaList.value = recyclerList
    }

    fun getListMontoMegas() {
        val arrayList: ArrayList<String> = ArrayList()
        arrayList.add("Paquete 100 Megas")
        arrayList.add("Paquete 200 Megas")
        arrayList.add("Paquete 500 Megas")
        val recyclerList = MontoRecargaList(arrayList)
        montoMegasList.value = recyclerList
    }
}