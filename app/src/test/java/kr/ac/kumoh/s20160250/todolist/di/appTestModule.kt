package kr.ac.kumoh.s20160250.todolist.di

import kr.ac.kumoh.s20160250.todolist.data.repository.TestToDoRepository
import kr.ac.kumoh.s20160250.todolist.data.repository.ToDoRepository
import kr.ac.kumoh.s20160250.todolist.domain.todo.*
import kr.ac.kumoh.s20160250.todolist.presentation.detail.DetailMode
import kr.ac.kumoh.s20160250.todolist.presentation.detail.DetailViewModel
import kr.ac.kumoh.s20160250.todolist.presentation.list.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


internal val appTestModule = module {
    //ViewModel
    viewModel { ListViewModel(get(), get(), get()) }
    viewModel { ( detailMode: DetailMode, id: Long )-> DetailViewModel(detailMode = detailMode, id = id, get(), get(), get(),get()) }
    //UseCase
    factory { InsertToDoItemUseCase(get()) }
    factory { GetToDoListUseCase(get()) }
    factory { InsertToDoListUseCase(get()) }
    factory { DeleteAllToDoItemUseCase(get()) }
    factory { DeleteToDoItemUseCase(get()) }

    factory { UpdateToDoUseCase(get()) }
    factory { GetToDoItemUseCase(get()) }
    //repository
    single<ToDoRepository> { TestToDoRepository() }
}