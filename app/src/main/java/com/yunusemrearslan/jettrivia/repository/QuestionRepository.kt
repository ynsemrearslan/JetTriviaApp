package com.yunusemrearslan.jettrivia.repository

import android.util.Log
import com.yunusemrearslan.jettrivia.data.DataOrException
import com.yunusemrearslan.jettrivia.model.QuestionItem
import com.yunusemrearslan.jettrivia.network.QuestionApi
import javax.inject.Inject

class QuestionRepository @Inject constructor(private val api:QuestionApi) {
    private val dataOrException =DataOrException<ArrayList<QuestionItem>,Boolean,Exception>()

    suspend fun getAllQuestions():DataOrException<ArrayList<QuestionItem>,Boolean,Exception>{
        try {
            dataOrException.loading = true
            dataOrException.data = api.getAllQuestion()
            if(dataOrException.data.toString().isNotEmpty()) dataOrException.loading =false

        }catch (exception:Exception){
            dataOrException.e = exception
            Log.d("Error",exception.localizedMessage)
        }
        return dataOrException
    }
}