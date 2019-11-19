package com.m.mockito.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.m.mockito.repoviewmodel.APIsStatus
import com.m.mockito.repoviewmodel.ToDoRepository
import com.m.mockito.repoviewmodel.ToDoViewMode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.*
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TestViewModelToDo {
    /*InstantTaskExecutorRule is needed to test code with LiveData. Without it, we would get an error:
    RuntimeException: Method getMainLooper in android.os.Looper not mocked.
    */
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainRule = MainRule(this)

    @Mock
    private lateinit var toDoRepository: ToDoRepository

    @Mock
    private lateinit var viewStatObserver: Observer<Any>

    private lateinit var toDoViewMode: ToDoViewMode

    @Test
    fun should_sucess_when_get_data_from_api() {

        toDoViewMode = ToDoViewMode(toDoRepository, mainRule.testCoroutineDispatcher)

        val data = emptyList<String>()
        val dataStat = APIsStatus.DataStat(data)

        mainRule.testCoroutineDispatcher.runBlockingTest() {
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

        toDoViewMode = ToDoViewMode(toDoRepository, mainRule.testCoroutineDispatcher)

        val data = emptyList<String>()
        val dataStat = APIsStatus.DataStat(data)

        mainRule.testCoroutineDispatcher.runBlockingTest() {
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

class MainRule(private val clazz: Any) : TestRule {

    val testCoroutineDispatcher = TestCoroutineDispatcher()
    val scope = TestCoroutineScope(testCoroutineDispatcher)

    override fun apply(base: Statement?, description: Description?): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                Dispatchers.setMain(testCoroutineDispatcher)
                MockitoAnnotations.initMocks(clazz)
                base!!.evaluate()
                Dispatchers.resetMain()
                scope.cleanupTestCoroutines()
            }
        }
    }

}
