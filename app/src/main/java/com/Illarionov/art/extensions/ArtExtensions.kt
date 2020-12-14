package com.Illarionov.art.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.Illarionov.art.App
import com.Illarionov.art.factories.ViewModelFactory

inline fun <T> Fragment.observe(data: LiveData<T>, crossinline callback: (T) -> Unit) =
    data.observe(viewLifecycleOwner, Observer { event -> event?.let { callback(it) } })

inline fun <T> AppCompatActivity.observe(data: LiveData<T>, crossinline callback: (T) -> Unit) =
    data.observe(this, Observer { event -> event.let { callback(it) } })

inline val Fragment.factory: ViewModelFactory
    get() = (requireActivity().application as App).factory
