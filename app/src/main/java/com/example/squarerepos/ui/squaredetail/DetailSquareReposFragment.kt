package com.example.squarerepos.ui.squaredetail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.squarerepos.R
import com.example.squarerepos.core.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_detail_repos.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailSquareReposFragment : BaseFragment<DetailReposIntent, DetailReposViewState, DetailReposViewEvent,
        DetailReposViewModel>(R.layout.fragment_detail_repos) {
    override val viewModel by viewModel<DetailReposViewModel>()

    private val detailReposArgs: DetailSquareReposFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadModule()

        setViews()
        addObservers()

        viewModel.intentChannel.offer(DetailReposIntent.OnStart(detailReposArgs.repoName))
    }

    private fun setViews() {
        requireActivity().title = getString(R.string.detail_repos_title, detailReposArgs.repoName)
    }

    override fun renderState(state: DetailReposViewState) {
        when (state) {
            is DetailReposViewState.Success -> {
                with(state.data) {
                    tv_star.text = starCount
                    tv_fork.text = forkCount
                    tv_full_name.text = fullName
                    tv_description.text = description
                    tv_url.text = url
                    tv_language.text = language
                    tv_watcher.text = watcherCount
                    tv_contributor.text = issuesCount
                }
            }
            else -> Unit
        }
    }

    override fun renderEvent(event: DetailReposViewEvent) {
        when (event) {
            is DetailReposViewEvent.ShowToast -> Toast.makeText(
                requireActivity(), event.message, Toast.LENGTH_SHORT
            ).show()
        }
    }
}