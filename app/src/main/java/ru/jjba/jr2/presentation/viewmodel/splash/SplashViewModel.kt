package ru.jjba.jr2.presentation.viewmodel.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import ru.jjba.jr2.domain.usecase.TestDataUseCase
import ru.jjba.jr2.presentation.viewmodel.base.BaseViewModel
import ru.jjba.jr2.presentation.viewmodel.util.defaultValue

class SplashViewModel(
    private val testDataUseCase: TestDataUseCase = TestDataUseCase()
) : BaseViewModel() {
    private val isAllowedToNavToMain = MutableLiveData<Boolean>().defaultValue(true)

    init {
        //setupDbData()
    }

    fun observeIsAllowedToNavToMain(): LiveData<Boolean> = isAllowedToNavToMain

    private fun setupDbData() = launch {
        testDataUseCase.setupDbData()
    }.invokeOnCompletion {
        isAllowedToNavToMain.postValue(true)
    }
}