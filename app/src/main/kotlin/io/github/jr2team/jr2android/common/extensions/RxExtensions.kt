package io.github.jr2team.jr2android.common.extensions

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.Subject

fun <T> CompositeDisposable.subscribe(
    subject: Subject<T>,
    onNext: (t: T) -> Unit
) {
    val disposable = subject.distinctUntilChanged().subscribe(onNext)
    this.add(disposable)
}

fun <T> CompositeDisposable.subscribe(
    subject: Subject<T>,
    onNext: (t: T) -> Unit,
    onError: ((t: Throwable) -> Unit)? = null,
    onComplete: (() -> Unit)? = null
) {
    val disposable = subject.subscribe(onNext, onError, onComplete)
    this.add(disposable)
}