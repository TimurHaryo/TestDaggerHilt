package id.train.hilter2.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import id.train.hilter2.data.local.BlogDao
import id.train.hilter2.data.local.CacheMapper
import id.train.hilter2.data.network.Api
import id.train.hilter2.data.network.NetworkMapper
import id.train.hilter2.repository.MainRepository
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        blogDao: BlogDao,
        api: Api,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): MainRepository {
        return MainRepository(blogDao, api, cacheMapper, networkMapper)
    }
}