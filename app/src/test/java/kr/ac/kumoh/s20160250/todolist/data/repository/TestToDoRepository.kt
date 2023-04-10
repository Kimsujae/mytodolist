package kr.ac.kumoh.s20160250.todolist.data.repository

import kr.ac.kumoh.s20160250.todolist.data.entity.ToDoEntity

class TestToDoRepository : ToDoRepository {

    private val toDoList: MutableList<ToDoEntity> = mutableListOf()

    override suspend fun getToDoList(): List<ToDoEntity> = toDoList


    override suspend fun insertToDoList(toDoList: List<ToDoEntity>) {
        this.toDoList.addAll(toDoList)
    }

    override suspend fun insertToDoItem(toDoItem: ToDoEntity): Long {
        this.toDoList.add(toDoItem)
        return toDoItem.id
    }

    override suspend fun updateToDoItem(toDoItem: ToDoEntity) {
        val foundToDoEntity = toDoList.find { it.id == toDoItem.id }
        this.toDoList[toDoList.indexOf(foundToDoEntity)] = toDoItem

    }

    override suspend fun getToDoItem(itemId: Long): ToDoEntity? {
        return toDoList.find { it.id == itemId }
    }

    override suspend fun deleteAll() {
        this.toDoList.clear()
    }

    override suspend fun deleteToDoItem(itemId: Long) {
        val foundToDoEntity = toDoList.find { it.id == itemId }
        this.toDoList.removeAt(toDoList.indexOf(foundToDoEntity))

    }
}