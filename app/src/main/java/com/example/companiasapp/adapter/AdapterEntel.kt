package com.example.companiasapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.companiasapp.ClickRecycler
import com.example.companiasapp.model.CompaniasData
import com.example.companiasapp.R

class AdapterEntel(
    var getCompaniasList: ArrayList<CompaniasData>,
    var onClickRecicler: ClickRecycler
) : RecyclerView.Adapter<AdapterEntel.RecargasViewHolder>() {

    class RecargasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var lnlRecargas: LinearLayout
        var imgvCompania: ImageView
        var tvTiempoAire: TextView
        var onClickRecicler: ClickRecycler? = null

        init {
            lnlRecargas = itemView.findViewById(R.id.lnl_root)
            imgvCompania = itemView.findViewById(R.id.imgv_compania)
            tvTiempoAire = itemView.findViewById(R.id.tv_tiempo_aire)
            lnlRecargas.setOnClickListener(this)
        }

        fun bind(companias: CompaniasData, onClickRecicler: ClickRecycler) {
            imgvCompania.setImageResource(R.drawable.ic_entel)
            tvTiempoAire.text = companias.description
            this.onClickRecicler = onClickRecicler
        }

        override fun onClick(v: View?) {
            when (v?.id) {
                R.id.lnl_root -> {
                    onClickRecicler?.onClickRecycler(
                        "Entel",
                        tvTiempoAire.text.toString()

                    )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecargasViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.view_list, parent, false)
        return RecargasViewHolder(view)
    }

    override fun onBindViewHolder(recargasViewHolder: RecargasViewHolder, position: Int) {
        val companiaPosition: CompaniasData = getCompaniasList[position]
        recargasViewHolder.lnlRecargas.tag = position
        val onClickRecicler: ClickRecycler = onClickRecicler
        recargasViewHolder.bind(companiaPosition, onClickRecicler)
    }

    override fun getItemCount(): Int {
        return getCompaniasList.size
    }
}