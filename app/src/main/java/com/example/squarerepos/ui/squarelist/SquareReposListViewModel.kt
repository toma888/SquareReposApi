package com.example.squarerepos.ui.squarelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.squarerepos.core.base.BaseViewModel
import com.example.squarerepos.core.base.SingleEventLiveData
import com.example.squarerepos.data.mapper.toDisplayListSquareRepos
import com.example.squarerepos.domain.interactor.SquareReposInteractor
import com.example.squarerepos.ui.recyclerview.model.SquareReposListDisplayItem
import kotlinx.coroutines.launch
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
        viewModelScope.launch {
            try {
                val list = interactor.getSquareReposList()
                _listOfRepos.setValue(list.toDisplayListSquareRepos().toMutableList())
            } catch (e: Exception) {
                _message.setValue("An error occurred: ${e.message}")
            }
        }
    }
}