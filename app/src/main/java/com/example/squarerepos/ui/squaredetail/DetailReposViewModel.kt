package com.example.squarerepos.ui.squaredetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.squarerepos.core.base.SingleEventLiveData
import com.example.squarerepos.data.mapper.toDisplaySquareRepos
import com.example.squarerepos.domain.interactor.SquareReposInteractor
import kotlinx.coroutines.launch

class DetailReposViewModel(
    private val interactor: SquareReposInteractor
): ViewModel() {

    private var _detailRepos = MutableLiveData<DetailSquareRepos>()
    val detailRepos: LiveData<DetailSquareRepos>
        get() = _detailRepos

    private var _message = SingleEventLiveData<String>()
    val message: SingleEventLiveData<String>
        get() = _message

    fun loadDetailRepos(repoName: String) {
        viewModelScope.launch() {
            try {
                val detailRepos = interactor.getDetailSquareRepos(repoName)
                _detailRepos.setValue(detailRepos.toDisplaySquareRepos())
            } catch (e: Exception) {
                _message.setValue("An error occurred: ${e.message}")
            }
        }
    }
}