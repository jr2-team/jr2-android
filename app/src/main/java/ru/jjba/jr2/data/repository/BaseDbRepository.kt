package ru.jjba.jr2.data.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import ru.jjba.jr2.data.db.dao.BaseDao
import kotlin.coroutines.CoroutineContext

abstract class BaseDbRepository<ET> internal constructor(
        internal val dao: BaseDao<ET>
) : CoroutineScope {
    override val coroutineContext: CoroutineContext = Dispatchers.Default

    fun insertSingle(value: ET) =
            async { dao.insertSingle(value) }

    fun insertMany(values: List<ET>) =
            async { dao.insertMany(values) }
}