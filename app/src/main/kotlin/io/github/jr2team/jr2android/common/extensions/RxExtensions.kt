package io.github.jr2team.jr2android.common.extensions

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.Subject

fun <T> CompositeDisposable.subscribe(
    subject: Subject<T>,
    onNext: (t: T) -> Unit
) {
    this.add(subject.subscribe(onNext))
}

fun <T> CompositeDisposable.subscribe(
    subject: Subject<T>,
    onNext: (t: T) -> Unit,
    onError: ((t: Throwable) -> Unit)? = null,
    onComplete: (() -> Unit)? = null
) {
    this.add(subject.subscribe(onNext, onError, onComplete))
}