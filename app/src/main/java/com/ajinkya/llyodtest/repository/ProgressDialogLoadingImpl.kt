package com.ajinkya.llyodtest.repository

import android.content.Context
import com.ajinkya.llyodtest.listeners.IProgressListener
import javax.inject.Inject

class ProgressDialogLoadingImpl @Inject constructor(private val progressDialogLoading: IProgressListener) {
    fun showProgress(context: Context, message: String, isCancelable: Boolean) {
        progressDialogLoading.showProgress(context, message, isCancelable)
    }

    fun hideProgress() {
        progressDialogLoading.hideProgress()
    }
}
