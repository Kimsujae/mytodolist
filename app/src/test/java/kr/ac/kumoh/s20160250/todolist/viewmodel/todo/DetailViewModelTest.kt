package kr.ac.kumoh.s20160250.todolist.viewmodel.todo

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kr.ac.kumoh.s20160250.todolist.data.entity.ToDoEntity
import kr.ac.kumoh.s20160250.todolist.domain.todo.InsertToDoItemUseCase
import kr.ac.kumoh.s20160250.todolist.presentation.detail.DetailMode
import kr.ac.kumoh.s20160250.todolist.presentation.detail.DetailViewModel
import kr.ac.kumoh.s20160250.todolist.presentation.detail.ToDoDetailState
import kr.ac.kumoh.s20160250.todolist.presentation.list.ListViewModel
import kr.ac.kumoh.s20160250.todolist.presentation.list.ToDoListState
import kr.ac.kumoh.s20160250.todolist.viewmodel.ViewModelTest
import org.junit.Before
import org.junit.Test
import org.koin.test.inject
import org.koin.core.parameter.parametersOf


/**[DetailViewModel]을 테스트하기위한 Unit Test Class
 * 1. initData()
 * 2. test viewModel fetch
 * 3. test delete todo
 * 4. test update todo
 **/
@ExperimentalCoroutinesApi
internal class DetailViewModelTest : ViewModelTest() {

    private val id = 1L

    private val detailViewModel by inject<DetailViewModel> { parametersOf(DetailMode.DETAIL, id) }

    private val listViewModel by inject<ListViewModel>()

    private val insertToDoListUseCase: InsertToDoItemUseCase by inject()

    private val todo = ToDoEntity(
        id = id,
        title = "title $id",
        description = "description $id",
        hasCompleted = false
    )

    @Before
    fun init() {
        initData()
    }

    private fun initData() = runBlockingTest {
        insertToDoListUseCase(todo)
    }

    @Test
    fun test_viewModel_fetch() = runBlockingTest {
        val testObservable = detailViewModel.toDoDetailLiveData.test()
        detailViewModel.fetchData()
        testObservable.assertValueSequence(
            listOf(
                ToDoDetailState.UnInitialized,
                ToDoDetailState.Loading,
                ToDoDetailState.Success(todo)
            )

        )
    }

    @Test
    fun test_Delete_todo() = runBlockingTest {
        val detailTestObservable = detailViewModel.toDoDetailLiveData.test()
        detailViewModel.deleteTodo()

        detailTestObservable.assertValueSequence(
            listOf(
                ToDoDetailState.UnInitialized,
                ToDoDetailState.Loading,
                ToDoDetailState.Delete
            )
        )
        val listTestObservable = listViewModel.todoListLiveData.test()
        listViewModel.fetchData()
        listTestObservable.assertValueSequence(
            listOf(
                ToDoListState.UnInitialized,
                ToDoListState.Loading,
                ToDoListState.Success(listOf())
            )
        )
    }

    @Test
    fun test_update_todo() = runBlockingTest {
        val testObservable = detailViewModel.toDoDetailLiveData.test()

        val updateTitle = "title 1 update"
        val updateDescription = "description 1 update"

        val updateToDo = todo.copy(
            title = updateTitle,
            description = updateDescription
        )

        detailViewModel.writeToDo(
            title = updateTitle,
            description = updateDescription
        )
        testObservable.assertValueSequence(
            listOf(
                ToDoDetailState.UnInitialized,
                ToDoDetailState.Loading,
                ToDoDetailState.Success(updateToDo)
            )
        )
    }

}