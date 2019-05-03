package com.example.coinmarketcapexample.fragment

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection

open class BaseFragment : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            AndroidSupportInjection.inject(this)
        } catch (exception: Exception) {
            //ignore, we don't have to use dagger with all fragments
        }
    }

    open fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}