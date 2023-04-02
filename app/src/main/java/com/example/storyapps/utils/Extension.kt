package com.example.storyapps.utils

import com.example.storyapps.data.remote.respon.BaseResponse
import com.example.storyapps.data.remote.respon.ErrorResponse
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.lang.reflect.Type

fun <T> Throwable?.get(type: Type): BaseResponse<T> {
    try {
        if (this is HttpException) {
            val response = response()
            val err = Gson().fromJson<BaseResponse<T>>(response?.errorBody()?.charStream(), type)
            if (err?.isError == true) return err
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return BaseResponse(
        isError = true,
        message = "Telah terjadi kesalahan"
    )
}

fun <T> ResponseBody?.get(type: Type): BaseResponse<T> {
    try {
        val err = Gson().fromJson<BaseResponse<T>>(this?.charStream(), type)
        if (err?.isError == true) return err
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return BaseResponse(
        isError = true,
        message = "Telah terjadi kesalahan"
    )
}

fun ResponseBody?.get(): ErrorResponse {
    try {
        val err = Gson().fromJson(this?.charStream(), ErrorResponse::class.java)
        if (err?.isError == true) return err
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return ErrorResponse(
        isError = true,
        message = "Telah terjadi kesalahan"
    )
}

