package com.example.companiasapp.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.companiasapp.R
import com.example.companiasapp.fragment.RecargasFragment
import com.example.companiasapp.utils.Const
import com.example.companiasapp.utils.DialogManager
import com.example.companiasapp.utils.PopupDialogFragmentDone

class RecargasActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recargas_activity)
        replaceFragment(RecargasFragment())
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit()
    }

    override fun onBackPressed() {
        DialogManager.popupDialogDonde(this@RecargasActivity,  listenerDone, Const.TYPE_CLOSE_SESSION)
    }

    private val listenerDone: PopupDialogFragmentDone.PopupListener = object : PopupDialogFragmentDone.PopupListener {
        override fun onClick(onClick: String) {
            if (onClick == "CLOSE_SESSION") {
                startActivity(Intent(this@RecargasActivity, MainActivity::class.java))
                finishAffinity()
            }
        }
    }
}