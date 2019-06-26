package io.github.jr2team.jr2android.presentation.viewmodel.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import io.github.jr2team.jr2android.domain.usecase.TestDataUseCase
import io.github.jr2team.jr2android.presentation.viewmodel.util.defaultValue

class SplashViewModel(
    private val testDataUseCase: TestDataUseCase = TestDataUseCase()
) : ViewModel() {
    private val isAllowedToNavToMain = MutableLiveData<Boolean>().defaultValue(false)

    init {
        setupDbData()
    }

    fun observeIsAllowedToNavToMain(): LiveData<Boolean> = isAllowedToNavToMain

    private fun setupDbData() = viewModelScope.launch {
        //testDataUseCase.setupDbData()
    }.invokeOnCompletion {
        isAllowedToNavToMain.postValue(true)
    }
}