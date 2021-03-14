package com.example.squarerepos.ui.squarelist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.squarerepos.R
import com.example.squarerepos.core.base.BaseFragment
import com.example.squarerepos.ui.recyclerview.adapter.ReposAdapter
import kotlinx.android.synthetic.main.fragment_repos_list.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class SquareReposListFragment : BaseFragment(R.layout.fragment_repos_list) {
    private val viewModel by viewModel<SquareReposListViewModel>()

    private lateinit var squareReposListAdapter: ReposAdapter

    private var loadJob: Job? = null

    private fun load() {
        // Make sure we cancel the previous job before creating a new one
        loadJob?.cancel()
        loadJob = lifecycleScope.launch {
            viewModel.loadReposList().collectLatest {
                squareReposListAdapter.submitData(it)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadModule()

        setListAdapters()
        setViews()

        load()
    }

    private fun setListAdapters() {
        squareReposListAdapter = ReposAdapter { item ->
            findNavController().navigate(
                SquareReposListFragmentDirections.actionSquareReposListFragmentToDetailSquareReposFragment(
                    item.name
                )
            )
        }

        squareReposListAdapter.addLoadStateListener { loadState ->
            // Only show the list if refresh succeeds.
            recycler_view.isVisible = loadState.source.refresh is LoadState.NotLoading
            // Show loading spinner during initial load or refresh.
            progress_bar.isVisible = loadState.source.refresh is LoadState.Loading

            // Toast on any error, regardless of whether it came from RemoteMediator or PagingSource
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error
                ?: loadState.refresh as? LoadState.Error
            errorState?.let {
                Toast.makeText(requireContext(), "\uD83D\uDE28 Wooops ${it.error}", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setViews() {
        requireActivity().title = getString(R.string.app_name)

        recycler_view?.apply {
            adapter = squareReposListAdapter
            layoutManager = LinearLayoutManager(requireActivity())
            addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
        }
    }
}