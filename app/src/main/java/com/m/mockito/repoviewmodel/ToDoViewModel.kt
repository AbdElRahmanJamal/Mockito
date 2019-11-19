package com.m.mockito.repoviewmodel

import androidx.lifecycle.liveData
import kotlinx.coroutines.CoroutineDispatcher

class ToDoViewMode(
    private val toDoRepository: ToDoRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) {
    fun getRemoteData() = liveData(coroutineDispatcher) {
        emit(APIsStatus.LoadingState)
        val data = toDoRepository.getData()
        emit(data)


    }
}

class ToDoRepository(private val toDoDataStore: ToDoDataStore) {
    suspend fun getData() = APIsStatus.DataStat(toDoDataStore.getData())

}

class ToDoDataStore() {
    suspend fun getData() = emptyList<String>()
}

sealed class APIsStatus<T : Any> {
    class DataStat<T : Any>(val value: T) : APIsStatus<T>()
    class ErrorState(val exception: Throwable) : APIsStatus<Nothing>()
    object LoadingState : APIsStatus<Nothing>()

}