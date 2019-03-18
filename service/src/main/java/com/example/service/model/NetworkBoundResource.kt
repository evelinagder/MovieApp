package com.example.service.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

const val WRONG_URL_MGS = "wrong_url"

/**
 * Created by evelina.derventska
 */
// This class is used in the cases that we don`t need to use the DB. For example login
// and isAnalyst Check
// RequestType: Type for the API response.
abstract class NetworkBoundResource<RequestType> {


    private val result = MediatorLiveData<Resource<RequestType>>()

    init {
        setValue(Resource.loading(null))
        fetchFromNetwork()
    }

    private fun setValue(newValue: Resource<RequestType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    private fun fetchFromNetwork() {
        val apiResponse = createCall() ?: return

        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            when (response) {
                is ApiSuccessResponse -> {
                    val result = processResponse(response)
                    setValue(Resource.success(result))
                }
                is ApiErrorResponse -> {
                    val msg = if (response.responseCode == 404) WRONG_URL_MGS else response.errorMessage
                    setValue(Resource.error(msg, null))
                }
                is ApiOfflineResponse -> {

                    setValue(Resource.offline(response.errorMessage, null))
                }
            }
        }
    }


    fun asLiveData() = result as LiveData<Resource<RequestType>>

    protected open fun processResponse(response: ApiSuccessResponse<RequestType>) = response.body

    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>?

}