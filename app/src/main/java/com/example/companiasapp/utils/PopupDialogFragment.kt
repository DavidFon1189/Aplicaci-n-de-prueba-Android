package com.example.companiasapp.utils

import android.app.Dialog
import android.graphics.Point
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.example.companiasapp.R
import kotlinx.android.synthetic.main.monto_recarga_activity.*
import kotlinx.android.synthetic.main.popup_dialog.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class PopupDialogFragment : DialogFragment(), View.OnClickListener {
    private lateinit var compania: String
    private lateinit var telefono: String
    private lateinit var monto: String
    private var listener: PopupListener? = null

    interface PopupListener {
        fun onClick(onClick: String)
    }

    companion object {

        val TAG: String = PopupDialogFragment::class.java.simpleName

        fun newInstance(
            compania: String,
            telefono: String,
            monto: String,
            listener: PopupListener
        ): PopupDialogFragment {
            val fragment = PopupDialogFragment()
            fragment.setListener(listener)
            fragment.setCompania(compania)
            fragment.setTelefono(telefono)
            fragment.setmonto(monto)
            return fragment
        }
    }

    fun setCompania(compania: String) {
        this.compania = compania
    }

    fun setTelefono(telefono: String) {
        this.telefono = telefono
    }

    fun setmonto(monto: String) {
        this.monto = monto
    }

    fun setListener(listener: PopupListener?) {
        this.listener = listener
    }



    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
        if (dialog != null) {
            val window: Window? = dialog.getWindow()
            val point: Point = getSizeScreen(activity as AppCompatActivity)
            val x: Int = point.x / 9
            window?.setLayout(point.x - x, LinearLayout.LayoutParams.WRAP_CONTENT)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        // request a window without the title
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent);
        return dialog
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.popup_dialog, container, false)
        val tvMonto = view.findViewById<TextView>(R.id.tv_monto_recarga_popup)
        val tvtelefono = view.findViewById<TextView>(R.id.tv_telefono_recarga)
        val tvCompania = view.findViewById<TextView>(R.id.compania_popup)
        val btnAceptar = view.findViewById<Button>(R.id.btn_aceptar_popup)
        val btnCancelar = view.findViewById<Button>(R.id.btn_cancelar_popup)
        val tvFecha = view.findViewById<TextView>(R.id.tv_fecha)
        val tvHora = view.findViewById<TextView>(R.id.tv_hora)
        tvMonto.text = monto
        tvtelefono.text = telefono
        tvCompania.text = compania
        btnAceptar.setOnClickListener(this)
        btnCancelar.setOnClickListener(this)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val currentDateTime = LocalDateTime.now()
            tvFecha.text = currentDateTime?.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)).toString()
            tvHora.text = currentDateTime?.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)).toString()
        } else {
            val date: DateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            tvHora.text = date.format(Calendar.getInstance().time)
            val hour: DateFormat = SimpleDateFormat("h:mm:ss a", Locale(""))
            tvHora.text = hour.format(Calendar.getInstance().time)
        }
        return view
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_aceptar_popup -> {
                listener?.onClick("")
                dissmisDialog()
            }
            R.id.btn_cancelar_popup -> {
                dissmisDialog()
            }
        }
    }

    fun dissmisDialog() {
        dialog?.dismiss()
    }

    fun getSizeScreen(context: AppCompatActivity): Point {
        val display = context.windowManager?.defaultDisplay
        val size = Point()
        display?.getSize(size)
        return size
    }
}