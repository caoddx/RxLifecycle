package com.github.caoddx.rxlifecycle

import android.arch.lifecycle.GenericLifecycleObserver
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import io.reactivex.disposables.Disposable

fun Disposable.bindTo(owner: LifecycleOwner): Disposable {

    owner.lifecycle.addObserver(object : GenericLifecycleObserver {
        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {

            if (event == Lifecycle.Event.ON_DESTROY) {
                dispose()
            }

            if (isDisposed) {
                source.lifecycle.removeObserver(this)
            }
        }
    })

    return this
}