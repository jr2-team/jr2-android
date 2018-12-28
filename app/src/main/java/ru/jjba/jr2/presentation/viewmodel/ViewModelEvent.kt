package ru.jjba.jr2.presentation.viewmodel

// Used as a wrapper for data that is exposed via a LiveData that represents an event
open class ViewModelEvent<out T>(private val content: T) {
    var hasBeenHandled = false
        private set

    // Returns the content and prevents its use again
    fun getContentIfNotHandled(): T? = if (hasBeenHandled) {
        null
    } else {
        hasBeenHandled = true
        content
    }

    // Returns the content, even if it's already been handled
    fun peekContent(): T = content
}