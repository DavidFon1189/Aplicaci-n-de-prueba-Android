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

class AdapterTuenti (
    var getCompaniasList: ArrayList<CompaniasData>,
//    var onClickRecicler: ClickRecyclerAgency
) : RecyclerView.Adapter<AdapterTuenti.RecargasViewHolder>() {
    class RecargasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var lnlRecargas: LinearLayout
        var imgvCompania: ImageView
        var tvTiempoAire: TextView
//        var onClickRecicler: ClickRecyclerAgency? = null

        init {
            lnlRecargas = itemView.findViewById(R.id.lnl_root)
            imgvCompania = itemView.findViewById(R.id.imgv_compania)
            tvTiempoAire = itemView.findViewById(R.id.tv_tiempo_aire)
//            cardView.setOnClickListener(this)
        }

        //        fun bind(companias: CompaniasData, onClickRecicler: ClickRecyclerAgency)
        fun bind(companias: CompaniasData) {
//            imgvCompania.setImageURI(companias.owner)
            tvTiempoAire.text = companias.description
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecargasViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.view_list, parent, false)
        return AdapterTuenti.RecargasViewHolder(view)
    }

    override fun onBindViewHolder(recargasViewHolder: RecargasViewHolder, position: Int) {
        val companiaPosition: CompaniasData = getCompaniasList[position]
        recargasViewHolder.lnlRecargas.tag = position
//        val onClickRecicler: ClickRecyclerAgency = onClickRecicler
        recargasViewHolder.bind(companiaPosition)
//        recargasViewHolder.bind(agencyPosition, onClickRecicler)
    }

    override fun getItemCount(): Int {
        return getCompaniasList.size
    }
}