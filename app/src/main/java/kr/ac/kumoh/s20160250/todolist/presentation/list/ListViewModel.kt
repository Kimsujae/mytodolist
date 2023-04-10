package kr.ac.kumoh.s20160250.todolist.presentation.list

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kr.ac.kumoh.s20160250.todolist.NonNullMutableLiveData
import kr.ac.kumoh.s20160250.todolist.data.entity.ToDoEntity
import kr.ac.kumoh.s20160250.todolist.domain.todo.DeleteAllToDoItemUseCase
import kr.ac.kumoh.s20160250.todolist.domain.todo.GetToDoListUseCase
import kr.ac.kumoh.s20160250.todolist.domain.todo.InsertToDoListUseCase
import kr.ac.kumoh.s20160250.todolist.domain.todo.UpdateToDoUseCase
import kr.ac.kumoh.s20160250.todolist.presentation.BaseViewModel
import org.koin.androidx.compose.inject

/**
 * 필요한  UseCase
 * 1. [GetTodoListUseCase]
 * 2. [UpdateTodoUseCase]
 * 3. [DeleteAllToDoItemUseCase]
 */
internal class ListViewModel(
    private val getToDoListUseCase: GetToDoListUseCase,
    private val updateToDoUseCase: UpdateToDoUseCase,
    private val deleteAllToDoItemUseCase: DeleteAllToDoItemUseCase,

) : BaseViewModel() {



    private var _toDoListLiveData = MutableLiveData<ToDoListState>(ToDoListState.UnInitialized)
    val todoListLiveData: LiveData<ToDoListState> = _toDoListLiveData



    override fun fetchData() = viewModelScope.launch {
        _toDoListLiveData.postValue(ToDoListState.Loading)
        _toDoListLiveData.postValue(ToDoListState.Success(getToDoListUseCase()))
    }

    fun updateEntity(toDoEntity: ToDoEntity) = viewModelScope.launch {
        updateToDoUseCase(toDoEntity)
    }

    fun deleteAll() = viewModelScope.launch {
        _toDoListLiveData.postValue(ToDoListState.Loading)
        deleteAllToDoItemUseCase()
        _toDoListLiveData.postValue(ToDoListState.Success(getToDoListUseCase()))
    }
}