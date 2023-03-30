package kr.ac.kumoh.s20160250.todolist.di

import kr.ac.kumoh.s20160250.todolist.data.repository.TestToDoRepository
import kr.ac.kumoh.s20160250.todolist.data.repository.ToDoRepository
import kr.ac.kumoh.s20160250.todolist.domain.todo.GetToDoItemUseCase
import kr.ac.kumoh.s20160250.todolist.domain.todo.GetToDoListUseCase
import kr.ac.kumoh.s20160250.todolist.domain.todo.InsertToDoListUseCase
import kr.ac.kumoh.s20160250.todolist.domain.todo.UpdateToDoUseCase
import kr.ac.kumoh.s20160250.todolist.presentation.list.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


internal val appTestModule = module {
    //ViewModel
    viewModel { ListViewModel(get(), get()) }
    //UseCase
    factory { GetToDoListUseCase(get()) }
    factory { InsertToDoListUseCase(get()) }

    factory { UpdateToDoUseCase(get()) }
    factory { GetToDoItemUseCase(get()) }
    //repository
    single<ToDoRepository> { TestToDoRepository() }
}