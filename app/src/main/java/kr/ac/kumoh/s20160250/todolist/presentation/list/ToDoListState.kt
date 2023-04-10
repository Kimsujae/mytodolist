package kr.ac.kumoh.s20160250.todolist.presentation.list

import kr.ac.kumoh.s20160250.todolist.data.entity.ToDoEntity

sealed class ToDoListState {

    object UnInitialized : ToDoListState()

    object Loading : ToDoListState()

    data class Success(
        val toDoList: List<ToDoEntity>
    ) : ToDoListState()

    object Error : ToDoListState()
}
