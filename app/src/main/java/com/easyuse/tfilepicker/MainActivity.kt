package com.easyuse.tfilepicker

import com.easyuse.tfilepicker.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun init() {
        filePickerTv.setOnClickListener {
            TFilePicker
                .init(this)
                .single()
                .build()
        }
    }
}