package id.train.hilter2.repository

import id.train.hilter2.data.local.BlogDao
import id.train.hilter2.data.local.CacheMapper
import id.train.hilter2.data.network.Api
import id.train.hilter2.data.network.NetworkMapper
import id.train.hilter2.model.Blog
import id.train.hilter2.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class MainRepository
constructor(
    private val blogDao: BlogDao,
    private val api: Api,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
) {
    suspend fun getBlog(): Flow<DataState<List<Blog>>> = flow {
        emit(DataState.Loading)
        delay(2000) // don't do this in production
        try {
            val networkBlogs = api.getData()
            val blogs = networkMapper.mapFromEntityList(networkBlogs)
            for (blog in blogs) {
                blogDao.insert(cacheMapper.mapToEntity(blog))
            }
            val cachedBlogs = blogDao.get()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlogs)))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}