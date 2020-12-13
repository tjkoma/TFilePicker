package com.easyuse.tfilepicker.uitls

class FileUtil {
    companion object{
        /**
         * 获取文件后缀名
         */
        fun getExtensionName(filename: String): String {
            if (filename.isNotEmpty()) {
                var dot = filename.lastIndexOf('.')
                if (dot > -1 && (dot < filename.length - 1)) {
                    return filename.substring(dot + 1)
                }
            }
            return filename
        }

    }
}