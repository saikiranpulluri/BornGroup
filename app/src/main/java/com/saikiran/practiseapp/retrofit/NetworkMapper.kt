package com.saikiran.practiseapp.retrofit

import com.saikiran.practiseapp.model.GitRepo
import com.saikiran.practiseapp.util.EntityMapper
import javax.inject.Inject

class NetworkMapper @Inject constructor() : EntityMapper<GitRepoNetworkEntity, GitRepo> {
    override fun mapFromEntity(entity: GitRepoNetworkEntity): GitRepo {
        return GitRepo(
            id = entity.id,
            repoName = entity.name,
            description = entity.description,
            visibility = entity.visibility
        )
    }

    override fun mapToEntity(domainModel: GitRepo): GitRepoNetworkEntity {
        return GitRepoNetworkEntity(
            id = domainModel.id,
            name = domainModel.repoName,
            description = domainModel.description,
            visibility = domainModel.visibility
        )
    }

    fun mapFromEntityList(entities: List<GitRepoNetworkEntity>): List<GitRepo> {
        return entities.map { mapFromEntity(it) }
    }
}