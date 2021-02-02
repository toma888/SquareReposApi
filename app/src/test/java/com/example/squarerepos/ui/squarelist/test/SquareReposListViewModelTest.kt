package com.example.squarerepos.ui.squarelist.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.squarerepos.data.mapper.toDisplayListSquareRepos
import com.example.squarerepos.domain.interactor.SquareReposInteractor
import com.example.squarerepos.network.ResultWrapper
import com.example.squarerepos.network.model.SquareReposResponse
import com.example.squarerepos.ui.recyclerview.model.SquareReposListDisplayItem
import com.example.squarerepos.ui.squarelist.SquareReposListViewModel
import com.example.squarerepos.ui.squarelist.squareReposListModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.GlobalContext.loadKoinModules
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class SquareReposListViewModelTest : KoinTest {
    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val viewModel: SquareReposListViewModel by inject()
    @Mock
    private lateinit var interactor: SquareReposInteractor
    @Mock
    private lateinit var listOfReposObserver: Observer<MutableList<SquareReposListDisplayItem>>
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.initMocks(this)

        startKoin { modules(squareReposListModule) }
        loadKoinModules(module { single(override=true) { interactor } })
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testScope.cleanupTestCoroutines()
        stopKoin()
    }

    @Test
    fun loadReposList() {
        viewModel.listOfRepos.observeForever(listOfReposObserver)
        testScope.runBlockingTest {
            val result = ResultWrapper.Success(listOf<SquareReposResponse>())
            `when`(interactor.getSquareReposList()).thenReturn(result)
            viewModel.loadReposList()
            verify(listOfReposObserver).onChanged( result.data.toDisplayListSquareRepos().toMutableList())
        }
    }
}