package id.train.hilter2.data.network

import id.train.hilter2.data.network.entity.BlogNetworkEntity
import id.train.hilter2.model.Blog
import id.train.hilter2.util.EntityMapper
import javax.inject.Inject

class NetworkMapper
@Inject constructor(): EntityMapper<BlogNetworkEntity, Blog> {
    override fun mapFromEntity(networkEntity: BlogNetworkEntity): Blog {
        return Blog(
            id = networkEntity.pk,
            title = networkEntity.title,
            body = networkEntity.body,
            image = networkEntity.image,
            category = networkEntity.category
        )
    }

    override fun mapToEntity(domainModel: Blog): BlogNetworkEntity {
        return BlogNetworkEntity(
            pk = domainModel.id,
            title = domainModel.title,
            body = domainModel.body,
            image = domainModel.image,
            category = domainModel.category
        )
    }

    fun mapFromEntityList(networkEntities: List<BlogNetworkEntity>): List<Blog> {
        return networkEntities.map { mapFromEntity(it) }
    }
}