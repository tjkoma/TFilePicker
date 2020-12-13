package com.easyuse.tfilepicker.entity

data class FileEntity(
    val isDirectory: Boolean = false,
    val name: String,
    val absolutePath: String
)