package pl.dmcs.pb.richchat.app

import io.reactivex.disposables.CompositeDisposable

/**
 * Created by howor on 23.12.2017.
 */

abstract class BasePresenter : Presenter {

    protected val disposable: CompositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        disposable.clear()
    }
}