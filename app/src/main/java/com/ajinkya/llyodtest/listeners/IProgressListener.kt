package com.ajinkya.llyodtest.listeners

import android.content.Context

interface IProgressListener {
    fun showProgress(context: Context, message: String, isCancelable: Boolean)
    fun hideProgress()
}