package com.Illarionov.art

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

class ArtImmHelperImpl(private val view: View?) : ArtImmHelper {
    private val imm: InputMethodManager by lazy {
        view?.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager }

    override fun showSoftInput() {
        view?.requestFocus()
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }

    override fun hideSoftInput() {
        view?.clearFocus()
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}