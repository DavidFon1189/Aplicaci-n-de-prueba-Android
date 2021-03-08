package com.example.companiasapp.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class DialogManager {

    companion object{
        fun progressBar(context: AppCompatActivity): ProgressBar {
            val fm: FragmentManager = context.supportFragmentManager
            eliminaDialogFragment(fm, ProgressBar.TAG)
            val dialogFragment: ProgressBar =
                ProgressBar.newInstance()
            dialogFragment.isCancelable = false
            try {
                dialogFragment.show(fm, ProgressBar.TAG)
            } catch (ignored: Exception) {
            }
            return dialogFragment
        }

        fun popupDialog(context: AppCompatActivity, compania: String, telefono: String, monto: String,
                        listener: PopupDialogFragment.PopupListener): PopupDialogFragment {
            val fm: FragmentManager = context.supportFragmentManager
            eliminaDialogFragment(fm, PopupDialogFragment.TAG)
            val dialogFragment: PopupDialogFragment =
                PopupDialogFragment.newInstance(compania, telefono, monto, listener)
            dialogFragment.isCancelable = false
            try {
                dialogFragment.show(fm, PopupDialogFragment.TAG)
            } catch (ignored: Exception) {
            }
            return dialogFragment
        }

        fun popupDialogDonde(context: AppCompatActivity, listener: PopupDialogFragmentDone.PopupListener, poppType: Int
        ): PopupDialogFragmentDone {
            val fm: FragmentManager = context.supportFragmentManager
            eliminaDialogFragment(fm, PopupDialogFragmentDone.TAG)
            val dialogFragment: PopupDialogFragmentDone =
                PopupDialogFragmentDone.newInstance(listener, poppType)
            dialogFragment.isCancelable = false
            try {
                dialogFragment.show(fm, PopupDialogFragmentDone.TAG)
            } catch (ignored: Exception) {
            }
            return dialogFragment
        }

        private fun eliminaDialogFragment(fm: FragmentManager, tag: String) {
            val fragment: Fragment? = fm.findFragmentByTag(tag)
            if (fragment != null) {
                try {
                    fm.beginTransaction().remove(fragment).commit()
                } catch (ignored: java.lang.Exception) {
                }
            }
        }
    }
}