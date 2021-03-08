package com.example.companiasapp.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.companiasapp.InterfaceListenerMontoRecarga
import com.example.companiasapp.model.MontoRecargaList
import com.example.companiasapp.R
import com.example.companiasapp.databinding.MontoRecargaActivityBinding
import com.example.companiasapp.utils.Const
import com.example.companiasapp.utils.DialogManager
import com.example.companiasapp.utils.PopupDialogFragment
import com.example.companiasapp.utils.PopupDialogFragmentDone
import com.example.companiasapp.viewmodel.MontoRecargaViewModel
import kotlinx.android.synthetic.main.monto_recarga_activity.*
import kotlinx.android.synthetic.main.toolbar.*

class MontoRecargaActivity: AppCompatActivity(), InterfaceListenerMontoRecarga, View.OnClickListener {

    private lateinit var dataBinding: MontoRecargaActivityBinding
    var compania = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.monto_recarga_activity)
        val viewModel = ViewModelProvider(this).get(MontoRecargaViewModel::class.java)
        compania = intent.getStringExtra("compania")
        val tipoRecarga = intent.getStringExtra("tipoRecarga")
        lnl_titulo_toolbar.visibility = View.VISIBLE
        lnl_settings_toolbar.visibility = View.GONE
        lnl_titulo_toolbar.setOnClickListener(this)
        if (compania == "Claro")
            dataBinding.tvCompaniaTitulo.setBackgroundResource(R.drawable.ic_claro)
        else if (compania == "Tuenti")
            dataBinding.tvCompaniaTitulo.setBackgroundResource(R.drawable.ic_tuenti)
        else dataBinding.tvCompaniaTitulo.setBackgroundResource(R.drawable.ic_entel)
//        dataBinding.tvCompaniaTitulo.text = compania
        dataBinding.montoRecargaViewModel = viewModel
        viewModel.listenerInterfaceMontoRecarga = this
        spinner_monto.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                dataBinding.tvMontoRecarga.text = (parent.getItemAtPosition(position).toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }


        if (tipoRecarga == "Tiempo Aire") {
            viewModel.getListMontoRecarga()
            viewModel.getListDataObserverMontoRecarga().observe(this, Observer<MontoRecargaList> {
                setArrayListMonto(it.items)
            })
        } else {
            viewModel.getListMontoMegas()
            viewModel.getListDataObserverMontoMegas().observe(this, Observer<MontoRecargaList> {
                setArrayListMonto(it.items)
            })
        }
    }

    override fun setDataMontoRecarga() {
        //Call the Popup
        DialogManager.popupDialog(this, compania, edt_telefono_recarga.text.toString(), tv_monto_recarga.text.toString(), listener)
    }

    override fun error(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun setArrayListMonto(montoList: ArrayList<String>?) {
        montoList?.add(0, resources.getString(R.string.selecciona_monto))
        if (!montoList.isNullOrEmpty()) {
            spinner_monto.adapter =
                ArrayAdapter(this, R.layout.spinner_item, montoList).apply {
                    setDropDownViewResource(R.layout.spinner_dialog_item)
                }
        }
    }

    private val listener: PopupDialogFragment.PopupListener = object : PopupDialogFragment.PopupListener {
        override fun onClick(onClick: String) {
            DialogManager.popupDialogDonde(this@MontoRecargaActivity,  listenerDone, Const.TYPE_DONE)
        }
    }

    private val listenerDone: PopupDialogFragmentDone.PopupListener = object : PopupDialogFragmentDone.PopupListener {
        override fun onClick(onClick: String) {
            //Star activity main
            startActivity(Intent(this@MontoRecargaActivity, RecargasActivity::class.java))
            finishAffinity()
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.lnl_titulo_toolbar -> {
                finish()
            }
        }
    }
}