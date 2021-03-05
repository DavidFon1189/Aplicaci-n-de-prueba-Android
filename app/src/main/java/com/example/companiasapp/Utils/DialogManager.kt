package com.example.companiasapp.Utils

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