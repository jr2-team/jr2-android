package io.github.jr2team.jr2android.data.repository

import io.github.jr2team.jr2android.data.database.dao.BaseDao

abstract class BaseDbRepository<ET> internal constructor(
    internal val dao: BaseDao<ET>
) {
    suspend fun insertSingle(value: ET) = dao.insertSingle(value)

    suspend fun insertMany(values: List<ET>) = dao.insertMany(values)
}