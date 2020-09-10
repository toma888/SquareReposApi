package com.example.squarerepos.core.di.module

import com.example.squarerepos.data.SquareRepository
import com.example.squarerepos.network.api.SquareReposAPI
import com.example.squarerepos.network.repository.SquareRepositoryImp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class RepositoryModule {

    @Singleton
    @Provides
    fun getSquareRepository(api: SquareReposAPI):
            SquareRepository = SquareRepositoryImp(api)

}