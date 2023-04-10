package kr.ac.kumoh.s20160250.todolist.presentation.detail

import kr.ac.kumoh.s20160250.todolist.data.entity.ToDoEntity

sealed class ToDoDetailState {

    object UnInitialized : ToDoDetailState()

    object Loading : ToDoDetailState()

    data class Success(
        val toDoItem: ToDoEntity
    ) : ToDoDetailState()

    object Delete : ToDoDetailState()

    object Modify : ToDoDetailState()

    object Error : ToDoDetailState()

    object Write : ToDoDetailState()
}
