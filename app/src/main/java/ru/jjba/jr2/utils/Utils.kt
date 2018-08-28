package ru.jjba.jr2.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.io.IOException
import java.nio.charset.Charset

fun ViewGroup.inflate(layoutRes: Int): View =
        LayoutInflater.from(context).inflate(layoutRes, this, false)

var View.isVisible: Boolean
    set(value) {
        this.visibility = if (value) View.VISIBLE else View.GONE
    }
    get() = this.visibility == View.VISIBLE

fun Context.loadJSONFromAsset(fileName: String): String? {
    var json: String? = null

    try {
        val inputStream = assets.open(fileName)
        val buffer = kotlin.ByteArray(inputStream.available()).also {
            inputStream.read(it)
            inputStream.close()
            json = String(it, Charset.forName("UTF-8"))
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }

    return json
}