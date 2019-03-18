package com.example.service.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

/**
 * Created by evelina.derventska
 */

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
                    val msg = response.errorMessage
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