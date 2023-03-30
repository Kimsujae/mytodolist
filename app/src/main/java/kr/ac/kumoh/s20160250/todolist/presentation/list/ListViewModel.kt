package kr.ac.kumoh.s20160250.todolist.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kr.ac.kumoh.s20160250.todolist.data.entity.ToDoEntity
import kr.ac.kumoh.s20160250.todolist.domain.todo.GetToDoListUseCase
import kr.ac.kumoh.s20160250.todolist.domain.todo.UpdateToDoUseCase

/**
 * 필요한  UseCase
 * 1. [GetTodoListUseCase]
 * 2. [UpdateTodoUseCase]
 * 3. [DeleteAllToDoItemUseCase]
 */
internal class ListViewModel(
    private val getToDoListUseCase: GetToDoListUseCase,
    private val updateToDoUseCase: UpdateToDoUseCase
) : ViewModel() {

    private var _toDoListLiveData = MutableLiveData<List<ToDoEntity>>()
    val todoListLiveData: LiveData<List<ToDoEntity>> = _toDoListLiveData

    fun fetchData(): Job = viewModelScope.launch {
        _toDoListLiveData.postValue(getToDoListUseCase())
    }
    fun updateEntity(toDoEntity: ToDoEntity) = viewModelScope.launch{
            updateToDoUseCase(toDoEntity)
    //        val success = updateToDoUseCase(toDoEntity)
//        if(success){
//
//        }
//        else{
//
//        }
    }
}