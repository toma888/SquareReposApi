package com.example.squarerepos.ui.squarelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.squarerepos.core.base.BaseViewModel
import com.example.squarerepos.core.base.SingleEventLiveData
import com.example.squarerepos.data.mapper.toDisplayListSquareRepos
import com.example.squarerepos.domain.interactor.SquareReposInteractor
import com.example.squarerepos.ui.recyclerview.model.SquareReposListDisplayItem
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SquareReposListViewModel @Inject constructor(
    private val interactor: SquareReposInteractor
) : BaseViewModel() {

    private var _listOfRepos = MutableLiveData<MutableList<SquareReposListDisplayItem>>()
    val listOfRepos: LiveData<MutableList<SquareReposListDisplayItem>>
        get() = _listOfRepos

    private var _message = SingleEventLiveData<String>()
    val message: SingleEventLiveData<String>
        get() = _message

    fun loadReposList() {
        compositeDisposable.add(
            interactor.getSquareReposList()
                .subscribeOn(Schedulers.newThread())
                .subscribe(
                    { list -> _listOfRepos.postValue(list.toDisplayListSquareRepos().toMutableList()) },
                    { e -> _message.postValue("An error occurred: ${e.message}") })
        )
    }
}