package com.example.squarerepos.ui.squarelist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
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

class SquareReposListFragment : BaseFragment(R.layout.fragment_repos_list) {
    val viewModel by viewModels<SquareReposListViewModel>(factoryProducer = { this.viewModelFactory })

    private lateinit var squareReposListAdapter: SquareReposListAdapterDiffUtils

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListAdapters()
        setViews()
        addObservers()

        viewModel.loadReposList()
    }

    private fun setListAdapters() {
        squareReposListAdapter = SquareReposListAdapterDiffUtils(
            AdapterDelegatesManager<SquareReposListDisplayItem>().apply {
                val itemOnClickListener: (SquareReposListDisplayItem.SquareReposListItem) -> Unit =
                    { item ->
                        findNavController().navigate(
                            SquareReposListFragmentDirections.actionSquareReposListFragmentToDetailSquareReposFragment(
                                item.name
                            )
                        )
                    }
                addDelegate(
                    SquareReposListAdapterDelegate(itemOnClickListener)
                )
            }
        )
    }

    private fun addObservers() {
        viewModel.listOfRepos
            .observe(viewLifecycleOwner, Observer<MutableList<SquareReposListDisplayItem>> { list ->
                squareReposListAdapter.setItems(list)
            })

        viewModel.message.observe(this, Observer { message ->
            if (!message.isNullOrEmpty()) {
                Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
            }
        })
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
}