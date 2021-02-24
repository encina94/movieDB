package com.brubank.movies.utils

class ImageUtils {
    companion object {
        fun getUrlImage(baseUrl: String, sizeImage: String, pathImage: String): String {
            return "$baseUrl$sizeImage/$pathImage"
        }
    }
}