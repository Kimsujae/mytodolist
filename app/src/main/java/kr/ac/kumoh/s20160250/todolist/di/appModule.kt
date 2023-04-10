package kr.ac.kumoh.s20160250.todolist.di

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kr.ac.kumoh.s20160250.todolist.data.local.db.ToDoDatabase
import kr.ac.kumoh.s20160250.todolist.data.repository.DefaultToDoRepository
import kr.ac.kumoh.s20160250.todolist.data.repository.ToDoRepository
import kr.ac.kumoh.s20160250.todolist.domain.todo.*
import kr.ac.kumoh.s20160250.todolist.domain.todo.DeleteAllToDoItemUseCase
import kr.ac.kumoh.s20160250.todolist.domain.todo.DeleteToDoItemUseCase
import kr.ac.kumoh.s20160250.todolist.domain.todo.GetToDoItemUseCase
import kr.ac.kumoh.s20160250.todolist.domain.todo.GetToDoListUseCase
import kr.ac.kumoh.s20160250.todolist.domain.todo.InsertToDoListUseCase
import kr.ac.kumoh.s20160250.todolist.presentation.detail.DetailMode
import kr.ac.kumoh.s20160250.todolist.presentation.detail.DetailViewModel
import kr.ac.kumoh.s20160250.todolist.presentation.list.ListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appModule = module {

    single { Dispatchers.Main }
    single { Dispatchers.IO }

    factory { GetToDoListUseCase(get()) }
    factory { GetToDoItemUseCase(get()) }
    factory { InsertToDoListUseCase(get()) }
    factory { InsertToDoItemUseCase(get()) }
    factory { DeleteToDoItemUseCase(get()) }
    factory { DeleteAllToDoItemUseCase(get()) }
    factory { UpdateToDoUseCase(get()) }

    single<ToDoRepository> { DefaultToDoRepository(get(), get()) }

    single { provideDB(androidApplication()) }
    single { provideToDoDao(get()) }

    viewModel { ListViewModel(get(), get(), get()) }
    viewModel { (detailMode: DetailMode, id: Long) -> DetailViewModel(detailMode, id, get(), get(), get(), get()) }
}
internal fun provideDB(context: Context): ToDoDatabase =
    Room.databaseBuilder(context, ToDoDatabase::class.java, ToDoDatabase.DB_NAME).build()

internal fun provideToDoDao(database: ToDoDatabase) = database.toDoDao()