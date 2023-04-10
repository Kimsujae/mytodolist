package kr.ac.kumoh.s20160250.todolist.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kr.ac.kumoh.s20160250.todolist.data.entity.ToDoEntity
import kr.ac.kumoh.s20160250.todolist.data.local.db.dao.ToDoDao

class DefaultToDoRepository(
    private val toDoDao: ToDoDao,
    private val ioDispatcher:CoroutineDispatcher
):ToDoRepository {


    override suspend fun getToDoList(): List<ToDoEntity> = withContext(ioDispatcher) {
        toDoDao.getAll()
    }

    override suspend fun getToDoItem(id: Long): ToDoEntity? = withContext(ioDispatcher) {
        toDoDao.getById(id)
    }

    override suspend fun insertToDoItem(toDoItem: ToDoEntity): Long = withContext(ioDispatcher) {
        toDoDao.insert(toDoItem)
    }

    override suspend fun insertToDoList(toDoList: List<ToDoEntity>) = withContext(ioDispatcher) {
        toDoDao.insert(toDoList)
    }

    override suspend fun updateToDoItem(toDoItem: ToDoEntity)= withContext(ioDispatcher) {
       toDoDao.update(toDoItem)
    }

    override suspend fun deleteToDoItem(id: Long)= withContext(ioDispatcher) {
        toDoDao.delete(id)
    }

    override suspend fun deleteAll() = withContext(ioDispatcher) {
        toDoDao.deleteAll()
    }
}