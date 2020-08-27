package com.sunnyweather.android.logic.network

import com.sunnyweather.android.logic.model.PlaceResponse
import retrofit2.*
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * 网络数据源访问入口，对所有网络请求API进行封装
 */
object SunnyWeatherNetWork {
    private val placeService = ServiceCreator.create(PlaceService::class.java)

    suspend fun searchPlaces(query : String) : PlaceResponse {
        return placeService.searchPlaces(query).await()
    }

    private suspend fun <T> Call<T>.await() : T {
        return suspendCoroutine {
            enqueue(object : Callback<T>{
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) {
                        it.resume(body)
                    } else {
                        it.resumeWithException(RuntimeException("response is null"))
                    }
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    it.resumeWithException(t)
                }
            })
        }
    }


}