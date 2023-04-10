package kr.ac.kumoh.s20160250.todolist.viewmodel.todo

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import kr.ac.kumoh.s20160250.todolist.data.entity.ToDoEntity
import kr.ac.kumoh.s20160250.todolist.presentation.detail.DetailMode
import kr.ac.kumoh.s20160250.todolist.presentation.detail.DetailViewModel
import kr.ac.kumoh.s20160250.todolist.presentation.detail.ToDoDetailState
import kr.ac.kumoh.s20160250.todolist.presentation.list.ListViewModel
import kr.ac.kumoh.s20160250.todolist.presentation.list.ToDoListState
import kr.ac.kumoh.s20160250.todolist.viewmodel.ViewModelTest
import org.junit.Test
import org.koin.core.parameter.parametersOf
import org.koin.test.inject

/**[DetailViewModel]을 테스트하기위한 Unit Test Class
 * 1. test viewModel fetch
 * 2. test insert todo
 **/
@ExperimentalCoroutinesApi
internal class DetailViewModelForWriteTest: ViewModelTest() {

    private val id = 0L

    private val detailViewModel by inject<DetailViewModel> { parametersOf(DetailMode.WRITE, id) }

    private val listViewModel by inject<ListViewModel>()

    private val todo = ToDoEntity(
        id = id,
        title = "title $id",
        description = "description $id",
        hasCompleted = false
    )
    @Test
    fun test_viewModel_fetch() = runBlockingTest {
        val testObserver = detailViewModel.toDoDetailLiveData.test()
        detailViewModel.fetchData()

        testObserver.assertValueSequence(
            listOf(
                ToDoDetailState.UnInitialized,
                ToDoDetailState.Write
            )

        )
    }
    @Test
    fun test_insert_todo() = runBlockingTest {
        val detailTestObservable = detailViewModel.toDoDetailLiveData.test()
        val listTestObservable = listViewModel.todoListLiveData.test()

        detailViewModel.writeToDo(
            title = todo.title,
            description = todo.description
        )
        detailTestObservable.assertValueSequence(
            listOf(
                ToDoDetailState.UnInitialized,
                ToDoDetailState.Loading,
                ToDoDetailState.Success(todo)
            )
        )
        assert(detailViewModel.detailMode==DetailMode.DETAIL)
        assert(detailViewModel.id==id)

        //뒤로나가서 리스트 보기
        listViewModel.fetchData()
        listTestObservable.assertValueSequence(
            listOf(
                ToDoListState.UnInitialized,
                ToDoListState.Loading,
                ToDoListState.Success(listOf(
                    todo
                ))
            )
        )
    }
}