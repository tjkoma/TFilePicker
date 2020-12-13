package com.easyuse.tfilepicker.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Environment
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.easyuse.tfilepicker.Constants
import com.easyuse.tfilepicker.R
import com.easyuse.tfilepicker.TFilePicker
import com.easyuse.tfilepicker.adapter.AllFileAdapter
import com.easyuse.tfilepicker.entity.FileEntity
import kotlinx.android.synthetic.main.activity_all_file_pick.*
import java.io.File

class AllFilePickActivity : BaseActivity() {

    private var selectMode =
        TFilePicker.SelectMode.SINGLE

    private var currentPath = ""

    private val fileList by lazy {
        arrayListOf<FileEntity>()
    }

    private val allFileAdapter by lazy {
        AllFileAdapter(fileList)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_all_file_pick
    }

    override fun init() {
        selectMode = intent.getSerializableExtra(Constants.SELECT_MODE) as TFilePicker.SelectMode
        backLl.setOnClickListener { finish() }
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                1
            )
        } else {
            folderScan(getHostPath())
        }
        allFileRv.layoutManager = LinearLayoutManager(this)
        allFileRv.adapter = allFileAdapter
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                folderScan(getHostPath())
            }
        }
    }

    private fun getHostPath(): String {
        currentPath = Environment.getExternalStorageDirectory().absolutePath
        return Environment.getExternalStorageDirectory().absolutePath
    }

    private fun folderScan(path: String): Array<File?> {
        val file = File(path)
        file.listFiles().forEach {
            var fileEntity = FileEntity(it.isDirectory, it.name, it.absolutePath)
            fileList.add(fileEntity)
        }
        allFileAdapter.notifyDataSetChanged()
        return file.listFiles()
    }

}