package com.saikiran.practiseapp.room

import com.saikiran.practiseapp.model.GitRepo
import com.saikiran.practiseapp.util.EntityMapper
import javax.inject.Inject

class CacheMapper @Inject constructor() : EntityMapper<GitRepoCacheEntity, GitRepo> {
    override fun mapFromEntity(entity: GitRepoCacheEntity): GitRepo {
        return GitRepo(
            id = entity.id,
            repoName = entity.repoName,
            description = entity.description,
            visibility = entity.visibility
        )
    }

    override fun mapToEntity(domainModel: GitRepo): GitRepoCacheEntity {
        return GitRepoCacheEntity(
            id = domainModel.id,
            repoName = domainModel.repoName,
            description = domainModel.description,
            visibility = domainModel.visibility
        )
    }

    fun mapFromEntityList(entities: List<GitRepoCacheEntity>): List<GitRepo> {
        return entities.map { mapFromEntity(it) }
    }
}