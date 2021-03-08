package com.example.companiasapp.utils

import android.app.Dialog
import android.graphics.Point
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.example.companiasapp.R

class PopupDialogFragmentDone : DialogFragment(), View.OnClickListener {

    private var listener: PopupListener? = null
    private var popupType: Int? = null
    private var actionType = ""
    interface PopupListener {
        fun onClick(onClick: String)
    }

    companion object {

        val TAG: String = PopupDialogFragmentDone::class.java.simpleName

        fun newInstance(listener: PopupListener, popupType: Int?): PopupDialogFragmentDone {
            val fragment = PopupDialogFragmentDone()
            fragment.setListener(listener)
            fragment.setPopupType(popupType)
            return fragment
        }
    }

    fun setPopupType(popupType: Int?) {
        this.popupType = popupType
    }

    fun setListener(listener: PopupListener?) {
        this.listener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.popup_done_dialog, container, false)
        val btnAceptar = view.findViewById<Button>(R.id.btn_aceptar_popup_done)
        val btnCancelar = view.findViewById<Button>(R.id.btn_cancelar_popup_done)
        val tvTitulo = view.findViewById<TextView>(R.id.tv_titulo_popup_donde)
        val imvSuccesPopup = view.findViewById<ImageView>(R.id.imv_succes_popup)
        btnAceptar.setOnClickListener(this)
        if (popupType == Const.TYPE_CLOSE_SESSION){
            tvTitulo.text = getString(R.string.seguro_cerrar_sesion)
            actionType = "CLOSE_SESSION"
            btnCancelar.visibility = View.VISIBLE
            btnCancelar.setOnClickListener(this)
            imvSuccesPopup.visibility = View.GONE
        }

        return view
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


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_aceptar_popup_done -> {
                listener?.onClick(actionType)
                dissmisDialog()
            }
            R.id.btn_cancelar_popup_done -> {
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