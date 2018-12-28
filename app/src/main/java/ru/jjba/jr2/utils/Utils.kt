package ru.jjba.jr2.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.io.IOException
import java.nio.charset.Charset

fun ViewGroup.inflate(layoutRes: Int): View =
        LayoutInflater.from(context).inflate(layoutRes, this, false)

var View.isVisible: Boolean
    set(value) {
        this.visibility = if (value) View.VISIBLE else View.GONE
    }
    get() = this.visibility == View.VISIBLE

fun Context.loadJSONFromAsset(fileName: String): String {
    var jsonStrContent = ""

    try {
        val inputStream = assets.open(fileName)
        val buffer = kotlin.ByteArray(inputStream.available()).also {
            inputStream.read(it)
            inputStream.close()
            jsonStrContent = String(it, Charset.forName("UTF-8"))
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }

    return jsonStrContent
}


fun <T : ViewModel> T.createFactory(): ViewModelProvider.Factory {
    val viewModel = this
    return object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T = viewModel as T
    }
}