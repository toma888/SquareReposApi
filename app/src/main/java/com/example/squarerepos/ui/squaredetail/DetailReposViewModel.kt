package com.example.squarerepos.ui.squaredetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.squarerepos.core.base.BaseViewModel
import com.example.squarerepos.core.base.SingleEventLiveData
import com.example.squarerepos.data.mapper.toDisplaySquareRepos
import com.example.squarerepos.domain.interactor.SquareReposInteractor
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailReposViewModel @Inject constructor(
    private val interactor: SquareReposInteractor
) : BaseViewModel() {

    private var _detailRepos = MutableLiveData<DetailSquareRepos>()
    val detailRepos: LiveData<DetailSquareRepos>
        get() = _detailRepos

    private var _message = SingleEventLiveData<String>()
    val message: SingleEventLiveData<String>
        get() = _message

    fun loadDetailRepos(repoName: String) {
        compositeDisposable.add(
            interactor.getDetailSquareRepos(repoName)
                .subscribeOn(Schedulers.newThread())
                .subscribe(
                    { detailRepos -> _detailRepos.postValue(detailRepos.toDisplaySquareRepos()) },
                    { e -> _message.postValue("An error occurred: ${e.message}") })
        )
    }
}