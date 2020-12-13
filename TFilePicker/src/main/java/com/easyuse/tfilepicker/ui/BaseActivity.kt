package com.easyuse.tfilepicker.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gyf.immersionbar.ImmersionBar

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(getLayoutId())
        ImmersionBar
            .with(this)
            .statusBarDarkFont(true)
            .fitsSystemWindows(true)
            .init()
        init()
    }

    abstract fun getLayoutId(): Int

    abstract fun init()
}