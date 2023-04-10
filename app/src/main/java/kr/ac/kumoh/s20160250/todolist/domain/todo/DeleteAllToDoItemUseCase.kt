package kr.ac.kumoh.s20160250.todolist.domain.todo

import kr.ac.kumoh.s20160250.todolist.data.entity.ToDoEntity
import kr.ac.kumoh.s20160250.todolist.data.repository.ToDoRepository
import kr.ac.kumoh.s20160250.todolist.domain.UseCase

internal class DeleteAllToDoItemUseCase(
    private val toDoRepository: ToDoRepository
) : UseCase {

    suspend operator fun invoke() = toDoRepository.deleteAll()

}