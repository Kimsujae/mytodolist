package kr.ac.kumoh.s20160250.todolist.data.repository

import kr.ac.kumoh.s20160250.todolist.data.entity.ToDoEntity

/**
 *  1. insertTodoList
 *  2. getTodoList
 *  3. updateToDoItem
 */
interface ToDoRepository {

    suspend fun getToDoList():List<ToDoEntity>

    suspend fun insertToDoList(toDoList: List<ToDoEntity>)

    suspend fun updateToDoItem(toDoItem:ToDoEntity):Boolean

    suspend fun getToDoItem(itemId: Long): ToDoEntity?
}