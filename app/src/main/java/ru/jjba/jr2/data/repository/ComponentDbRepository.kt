package ru.jjba.jr2.data.repository

import androidx.lifecycle.LiveData
import ru.jjba.jr2.App
import ru.jjba.jr2.data.db.AppDatabase
import ru.jjba.jr2.data.db.dao.ComponentDao
import ru.jjba.jr2.domain.entity.Component

class ComponentDbRepository(
        db: AppDatabase = App.instance.db
) : BaseDbRepository<Component>(db.getComponentDao()) {
    fun getAll(): LiveData<List<Component>> = (dao as ComponentDao).getAll()
}