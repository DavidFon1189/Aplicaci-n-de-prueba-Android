package com.example.companiasapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.companiasapp.CompaniasData
import com.example.companiasapp.R
import com.example.companiasapp.ServiciosData

class AdapterServicios(
    var getServicioList: ArrayList<ServiciosData>
) : RecyclerView.Adapter<AdapterServicios.ServiciosViewHolder>(){

    class ServiciosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var lnlServicios: LinearLayout
        var tvServicio: TextView
//        var onClickRecicler: ClickRecyclerAgency? = null


        init {
            lnlServicios = itemView.findViewById(R.id.lnl_root_servicios)
            tvServicio = itemView.findViewById(R.id.tv_servicio)
//            cardView.setOnClickListener(this)
        }

        //        fun bind(companias: CompaniasData, onClickRecicler: ClickRecyclerAgency)
        fun bind(servicios: ServiciosData) {

//            imgvCompania.setImageURI(companias.owner)
            tvServicio.text = servicios.servicio
//            this.onClickRecicler = onClickRecicler
        }

        override fun onClick(v: View?) {
//            when (v?.id) {
//                R.id.cv_agency -> {
//                    onClickRecicler?.onClickRecyclerAgency(
//                        agencia.text.toString(),
//                        direccion.text.toString(),
//                        telefono.text.toString(),
//                        postalCode
//                    )
//                }
//            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiciosViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.servicios_view, parent, false)
        return AdapterServicios.ServiciosViewHolder(view)
    }

    override fun onBindViewHolder(serviciosViewHolder: ServiciosViewHolder, position: Int) {
        val serviciosPosition: ServiciosData = getServicioList[position]
        serviciosViewHolder.lnlServicios.tag = position
//        val onClickRecicler: ClickRecyclerAgency = onClickRecicler
        serviciosViewHolder.bind(serviciosPosition)
//        recargasViewHolder.bind(agencyPosition, onClickRecicler)
    }

    override fun getItemCount(): Int {
        return getServicioList.size
    }
}