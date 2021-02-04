package com.example.squarerepos.ui.squarelist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.squarerepos.R
import com.example.squarerepos.core.base.BaseFragment
import com.example.squarerepos.ui.recyclerview.adapter.SquareReposListAdapterDiffUtils
import com.example.squarerepos.ui.recyclerview.base.AdapterDelegatesManager
import com.example.squarerepos.ui.recyclerview.delegate.SquareReposListAdapterDelegate
import com.example.squarerepos.ui.recyclerview.model.SquareReposListDisplayItem
import kotlinx.android.synthetic.main.fragment_repos_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class SquareReposListFragment : BaseFragment<SquareReposListIntent, SquareReposListViewState, SquareReposListViewEvent,
        SquareReposListViewModel>(R.layout.fragment_repos_list) {
    override val viewModel by viewModel<SquareReposListViewModel>()

    private lateinit var squareReposListAdapter: SquareReposListAdapterDiffUtils

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadModule()

        setListAdapters()
        setViews()
        addObservers()

        viewModel.intentChannel.offer(SquareReposListIntent.OnStart)
    }

    private fun setListAdapters() {
        squareReposListAdapter = SquareReposListAdapterDiffUtils(
            AdapterDelegatesManager<SquareReposListDisplayItem>().apply {
                val itemOnClickListener: (SquareReposListDisplayItem.SquareReposListItem) -> Unit =
                    { item -> viewModel.intentChannel.offer(SquareReposListIntent.OnDetailClicked(item.name)) }
                addDelegate(SquareReposListAdapterDelegate(itemOnClickListener))
            }
        )
    }

    private fun setViews() {
        requireActivity().title = getString(R.string.app_name)

        recycler_view?.apply {
            adapter = squareReposListAdapter
            layoutManager = LinearLayoutManager(requireActivity())
            addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
        }

        squareReposListAdapter.setItems(ArrayList())
    }

    override fun renderState(state: SquareReposListViewState) {
        progress_bar.isVisible = state is SquareReposListViewState.Loading
        when (state) {
            is SquareReposListViewState.Success -> squareReposListAdapter.setItems(state.data)
            else -> Unit
        }
    }

    override fun renderEvent(event: SquareReposListViewEvent) {
        when (event) {
            is SquareReposListViewEvent.ShowToast -> Toast.makeText(
                requireActivity(), event.message, Toast.LENGTH_SHORT
            ).show()
            is SquareReposListViewEvent.NavigateToDetail -> findNavController().navigate(
                SquareReposListFragmentDirections.actionSquareReposListFragmentToDetailSquareReposFragment(event.repoName)
            )
        }

    }
}