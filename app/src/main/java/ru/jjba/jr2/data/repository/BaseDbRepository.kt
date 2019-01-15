package ru.jjba.jr2.data.repository

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import ru.jjba.jr2.data.db.dao.BaseDao

abstract class BaseDbRepository<ET> internal constructor(
        internal val dao: BaseDao<ET>
) {
    suspend fun insertSingle(value: ET) = withContext(IO) {
        dao.insertSingle(value)
    }

    suspend fun insertMany(values: List<ET>) = withContext(IO) {
        dao.insertMany(values)
    }
}