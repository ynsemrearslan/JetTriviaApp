package com.yunusemrearslan.jettrivia.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yunusemrearslan.jettrivia.data.DataOrException
import com.yunusemrearslan.jettrivia.model.QuestionItem
import com.yunusemrearslan.jettrivia.repository.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class QuestionViewModel @Inject constructor(private val repository: QuestionRepository) :ViewModel(){
    val data: MutableState<DataOrException<ArrayList<QuestionItem>,Boolean,Exception>>
        = mutableStateOf(DataOrException(null,true,Exception("")))
    init {
        getAllQuestions()
    }

    private fun getAllQuestions(){
        viewModelScope.launch {
            data.value.loading = true
            data.value = repository.getAllQuestions()
            if(data.value.data.toString().isNotEmpty()){
                data.value.loading =false
            }
        }
    }
}