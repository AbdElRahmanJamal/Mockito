package com.m.mockito.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.m.mockito.repoviewmodel.APIsStatus
import com.m.mockito.repoviewmodel.ToDoRepository
import com.m.mockito.repoviewmodel.ToDoViewMode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TestViewModelToDo {
    /*InstantTaskExecutorRule is needed to test code with LiveData. Without it, we would get an error:
    RuntimeException: Method getMainLooper in android.os.Looper not mocked.
    */
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    val testCoroutineDispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var toDoRepository: ToDoRepository

    @Mock
    private lateinit var viewStatObserver: Observer<Any>

    private lateinit var toDoViewMode: ToDoViewMode

    @Before
    fun before() {
        Dispatchers.setMain(testCoroutineDispatcher)
        MockitoAnnotations.initMocks(this)
    }

    @After
    fun after() {
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun should_sucess_when_get_data_from_api() {

        toDoViewMode = ToDoViewMode(toDoRepository, testCoroutineDispatcher)

        val data = emptyList<String>()
        val dataStat = APIsStatus.DataStat(data)

        testCoroutineDispatcher.runBlockingTest() {
            //given
            Mockito.`when`(toDoRepository.getData()).thenReturn(dataStat)
            //when
            //toDoViewMode.getRemoteData()
            toDoViewMode.getRemoteData().observeForever(viewStatObserver)
            //then

            Mockito.verify(viewStatObserver, Mockito.times(1)).onChanged(APIsStatus.LoadingState)
            Mockito.verify(viewStatObserver).onChanged(dataStat)
        }
    }

    @Test
    fun should_sucess_when_get_data_from_api_without_observer() {

        toDoViewMode = ToDoViewMode(toDoRepository, testCoroutineDispatcher)

        val data = emptyList<String>()
        val dataStat = APIsStatus.DataStat(data)

        testCoroutineDispatcher.runBlockingTest() {
            //given
            Mockito.`when`(toDoRepository.getData()).thenReturn(dataStat)
            //when
            //toDoViewMode.getRemoteData()
            val remoteData = toDoViewMode.getRemoteData()
            //then
            Assert.assertEquals(remoteData.getOrAwaitValue(), APIsStatus.LoadingState)
            Assert.assertEquals(remoteData.getOrAwaitValue(), dataStat)

        }
    }
}
