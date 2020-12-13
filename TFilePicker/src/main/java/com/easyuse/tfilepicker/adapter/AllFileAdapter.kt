package com.easyuse.tfilepicker.adapter

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.easyuse.tfilepicker.R
import com.easyuse.tfilepicker.entity.FileEntity
import com.easyuse.tfilepicker.uitls.FileUtil

class AllFileAdapter(list: List<FileEntity>) :
    BaseQuickAdapter<FileEntity, BaseViewHolder>(R.layout.all_file_item_layout, list) {

    override fun convert(helper: BaseViewHolder?, item: FileEntity?) {
        val fileTypeIv: ImageView = helper!!.getView(R.id.file_type_iv)
        if (item!!.isDirectory) {
            Glide.with(mContext).load(R.mipmap.direatory_icon).into(fileTypeIv)
        } else {
            when (FileUtil.getExtensionName(item?.name.toString())) {
                "jpg", "jpeg", "png" -> Glide.with(mContext).load(item.absolutePath)
                    .into(fileTypeIv)
                "pdf" -> Glide.with(mContext).load(R.mipmap.pdf_icon).into(fileTypeIv)
                "doc", "docx" -> Glide.with(mContext).load(R.mipmap.doc_icon).into(fileTypeIv)
                else -> Glide.with(mContext).load(R.mipmap.file_icon).into(fileTypeIv)
            }
        }
        helper?.setText(R.id.file_name_tv, item?.name)
    }

}