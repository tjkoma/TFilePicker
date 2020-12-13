package com.easyuse.tfilepicker

import android.app.Activity
import android.content.Intent
import com.easyuse.tfilepicker.ui.AllFilePickActivity

class TFilePicker {


    private var mSelectMode: SelectMode = SelectMode.SINGLE

    companion object {
        const val SELECT_ALL_FILE_RESPONSE_CODE = 100

        var mActivity: Activity? = null

        val tFilePicker: TFilePicker by lazy {
            TFilePicker()
        }

        fun init(activity: Activity): TFilePicker {
            mActivity = activity
            tFilePicker.single()
            return tFilePicker
        }
    }

    enum class SelectMode {
        SINGLE, MULTI
    }


    fun single(): TFilePicker {
        setMode(mSelectMode)
        return tFilePicker
    }

    fun multi(): TFilePicker {
        setMode(SelectMode.MULTI)
        return tFilePicker
    }

    fun build(): TFilePicker {
        val intent = Intent(mActivity, AllFilePickActivity::class.java)
        intent.putExtra(Constants.SELECT_MODE, mSelectMode)
        mActivity?.startActivityForResult(intent, SELECT_ALL_FILE_RESPONSE_CODE)
        return tFilePicker
    }

    private fun setMode(selectMode: SelectMode) {
        mSelectMode = selectMode
    }

    fun getMode(selectMode: SelectMode): SelectMode {
        mSelectMode = selectMode
        return mSelectMode
    }

}