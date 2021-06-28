package com.decagonhq.clads.utils

import com.decagonhq.clads.models.ApiGenericResponseClass
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Retrofit
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class ErrorUtils @Inject constructor(var retrofit: Retrofit) {
    fun parseError(response: Response<*>): ApiGenericResponseClass<String> {
        val converter: Converter<ResponseBody, ApiGenericResponseClass<String>> = retrofit.responseBodyConverter(ApiGenericResponseClass::class.java, arrayOfNulls<Annotation>(0))
        return try {
            Timber.d(response.message())
            converter.convert(response.errorBody()!!)!!
        } catch (e: IOException) {
            Timber.d(e.message)
            return ApiGenericResponseClass(ERROR_MESSAGE, NULL_STRING, 69)
        }
    }
}