package com.ajinkya.llyodtest.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window
import android.widget.ProgressBar
import android.widget.TextView
import com.ajinkya.llyodtest.R
import com.ajinkya.llyodtest.listeners.IProgressListener

class CustomProgress : IProgressListener {
    private var mDialog: Dialog? = null
    override fun showProgress(context: Context, message: String, isCancelable: Boolean) {
        mDialog = Dialog(context)
        // no tile for the dialog
        mDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        mDialog!!.setContentView(R.layout.prograss_bar_dialog)
        mDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val mProgressBar = mDialog!!.findViewById<View>(R.id.progress_bar) as ProgressBar
        //  mProgressBar.getIndeterminateDrawable().setColorFilter(context.getResources()
        // .getColor(R.color.material_blue_gray_500), PorterDuff.Mode.SRC_IN);
        val progressText = mDialog!!.findViewById<View>(R.id.progress_text) as TextView
        progressText.text = message
        //progressText.setVisibility(View.VISIBLE);
        mProgressBar.visibility = View.VISIBLE
        // you can change or add this line according to your need
        mProgressBar.isIndeterminate = true
        mDialog!!.setCancelable(isCancelable)
        mDialog!!.setCanceledOnTouchOutside(isCancelable)
        mDialog!!.show()
    }

    override fun hideProgress() {
        if (mDialog != null) {
            mDialog!!.dismiss()
            mDialog = null
        }
    }

    companion object {
        var customProgress: CustomProgress? = null
        val instance: CustomProgress?
            get() {
                if (customProgress == null) {
                    customProgress = CustomProgress()
                }
                return customProgress
            }
    }
}
