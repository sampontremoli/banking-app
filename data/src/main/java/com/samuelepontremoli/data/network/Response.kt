package com.samuelepontremoli.data.network

data class Response<RequestData>(
    var responseType: Status,
    var data: RequestData? = null,
    var error: Error? = null
)

enum class Status { SUCCESSFUL, ERROR, LOADING }