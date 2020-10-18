package com.teakave.noteapp.presentation.feature.common

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

class KeyboardUtil {

    fun hideKeyboard(view: View?) {
        val inputMethodManager =
            view?.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0)
    }

}
