package com.example.kumparan_test.exception

class ApiResponseException(val errorCode: String?, val errorMessage: String?) : Exception()