package kr.ac.kumoh.s20160250.todolist.viewmodel.todo


import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kr.ac.kumoh.s20160250.todolist.data.entity.ToDoEntity
import kr.ac.kumoh.s20160250.todolist.domain.todo.GetToDoItemUseCase
import kr.ac.kumoh.s20160250.todolist.domain.todo.InsertToDoListUseCase
import kr.ac.kumoh.s20160250.todolist.presentation.list.ListViewModel
import kr.ac.kumoh.s20160250.todolist.viewmodel.ViewModelTest
import org.junit.Before
import org.junit.Test
import org.koin.test.inject


//ListViewModel을 테스트하기위한 Unit Test Class
/**
 * 1. initData()
 * 2. viewModel fetch
 * 3. test Item Update
 * 4. test Item Delete all
 **/
@ExperimentalCoroutinesApi
internal class ListViewModelTest : ViewModelTest() {

    private val viewModel: ListViewModel by inject()

    private val insertToDoListUseCase: InsertToDoListUseCase by inject()

    private val getToDoItemUseCase: GetToDoItemUseCase by inject()

    private val mockList = (0 until 10).map {
        ToDoEntity(
            id = it.toLong(),
            title = "title $it",
            description = "description $it",
            hasCompleted = false
        )
    }

    /**
     *  필요한 Usecase들
     * /1. InsertToDoListUseCase
     *  2. GetTodoItemUseCase
     */

    @Before
    fun init() {
        initData()
    }

    private fun initData() = runBlockingTest {
        insertToDoListUseCase(mockList)
    }

    //Test: 입력된 데이터를 불러와서 검증한다
    @Test
    fun testViewModelFetch(): Unit = runBlockingTest {
        val testObservable = viewModel.todoListLiveData.test()
        viewModel.fetchData()
        testObservable.assertValueSequence(
            listOf(
                mockList
            )
        )

    }
    // test:데이터를 업데이트했을때 반영되는가
    @Test
    fun test_item_update(): Unit = runBlockingTest {
        val todo =ToDoEntity(
            id = 1,
            title = "title 1",
            description = "description 1",
            hasCompleted = true
        )
        viewModel.updateEntity(todo)
        assert(getToDoItemUseCase(todo.id)?.hasCompleted ?:false == todo.hasCompleted)
    }


}