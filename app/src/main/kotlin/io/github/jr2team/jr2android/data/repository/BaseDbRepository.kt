package io.github.jr2team.jr2android.data.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import io.github.jr2team.jr2android.data.db.dao.BaseDao
import kotlin.coroutines.CoroutineContext

abstract class BaseDbRepository<ET> internal constructor(
        internal val dao: BaseDao<ET>
) : CoroutineScope {
    override val coroutineContext: CoroutineContext = Dispatchers.Default

    suspend fun insertSingle(value: ET) = dao.insertSingle(value)

    suspend fun insertMany(values: List<ET>) = dao.insertMany(values)
}