package com.example.squarerepos.ui.squaredetail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.squarerepos.R
import com.example.squarerepos.core.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_detail_repos.*

class DetailSquareReposFragment : BaseFragment(R.layout.fragment_detail_repos) {
    val viewModel by viewModels<DetailReposViewModel>(factoryProducer = { this.viewModelFactory })

    private val detailReposArgs: DetailSquareReposFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViews()
        addObservers()

        viewModel.loadDetailRepos(detailReposArgs.repoName)
    }

    private fun addObservers() {
        viewModel.detailRepos
            .observe(viewLifecycleOwner, Observer { detailRepos ->
                renderDetailRepos(detailRepos)
            })

        viewModel.message.observe(this, Observer { message ->
            if (!message.isNullOrEmpty()) {
                Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setViews() {
        requireActivity().title = getString(R.string.detail_repos_title, detailReposArgs.repoName)
    }

    private fun renderDetailRepos(detailRepos: DetailSquareRepos) {
        tv_star.text = detailRepos.starCount
        tv_fork.text = detailRepos.forkCount
        tv_full_name.text = detailRepos.fullName
        tv_description.text = detailRepos.description
        tv_url.text = detailRepos.url
        tv_language.text = detailRepos.language
        tv_watcher.text = detailRepos.watcherCount
        tv_contributor.text = detailRepos.issuesCount
    }
}