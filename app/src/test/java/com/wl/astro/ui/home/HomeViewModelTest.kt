package com.wl.astro.ui.home

import com.wl.astro.TestSchedulerFacade
import com.wl.astro.common.database.AstroTable
import com.wl.astro.repo.HomeRepo
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by NirajM on 23/06/21.
 * Version 1.0
 */
class HomeViewModelTest {
    @Mock
    lateinit var repository: HomeRepo
    private var testSchedulerFacade = TestSchedulerFacade()
    lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = HomeViewModel(repository, testSchedulerFacade)
    }

    @Test
    fun shouldFetchAstroFromLocal() {
        Mockito.`when`(repository.getLocalAstroForToday()).thenAnswer { Single.just(getAstroTableList()) }
        Mockito.`when`(repository.getAstroData()).thenAnswer { Single.error<Throwable> { Throwable() } }
        viewModel.fetchAstroFromLocal(false)
    }

    @After
    fun tearDown() {
    }

    private fun getAstroTableList(): List<AstroTable> {
        return listOf(
            AstroTable(
                date = "2021-06-23", title = "title", desc = "desc", mediaType = "video", url = "https://google.com"
            )
        )
    }
}