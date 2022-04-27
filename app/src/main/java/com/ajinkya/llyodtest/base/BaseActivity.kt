package com.ajinkya.llyodtest.base

import androidx.appcompat.app.AppCompatActivity
import com.ajinkya.llyodtest.repository.ProgressDialogLoadingImpl
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
open class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var progressDialog: ProgressDialogLoadingImpl
}