package com.example.squarerepos.ui.squarelist.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.squarerepos.data.mapper.toDisplayListSquareRepos
import com.example.squarerepos.domain.interactor.SquareReposInteractor
import com.example.squarerepos.network.model.SquareReposResponse
import com.example.squarerepos.ui.recyclerview.model.SquareReposListDisplayItem
import com.example.squarerepos.ui.squarelist.SquareReposListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class SquareReposListViewModelTest {
    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: SquareReposListViewModel
    @Mock
    private lateinit var interactor: SquareReposInteractor
    @Mock
    private lateinit var listOfReposObserver: Observer<MutableList<SquareReposListDisplayItem>>
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.initMocks(this)
        viewModel = SquareReposListViewModel(interactor).apply {
            listOfRepos.observeForever(listOfReposObserver)
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testScope.cleanupTestCoroutines()
    }

    @Test
    fun loadReposList() {
        testScope.runBlockingTest {
            val data = mutableListOf<SquareReposResponse>()
            `when`(interactor.getSquareReposList()).thenReturn(data)
            viewModel.loadReposList()
            verify(listOfReposObserver).onChanged( data.toDisplayListSquareRepos().toMutableList())
        }
    }
}