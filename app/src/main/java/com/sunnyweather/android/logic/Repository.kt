package com.sunnyweather.android.logic

import androidx.lifecycle.liveData
import com.sunnyweather.android.logic.model.Weather
import com.sunnyweather.android.logic.network.SunnyWeatherNetWork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.lang.RuntimeException
import kotlin.coroutines.CoroutineContext

//仓库层，判断调用方请求去数据库还是网络调用
object Repository {
    fun searchPlace(query: String) = fire(Dispatchers.IO) {
        val placeResponse = SunnyWeatherNetWork.searchPlaces(query)
        if (placeResponse.status == "ok") {
            val place = placeResponse.places
            Result.success(place)
        } else {
            Result.failure(RuntimeException("response status is ${placeResponse.status}"))
        }
    }

    fun refreshWeather(lng : String, lat : String) = fire(Dispatchers.IO){
        coroutineScope {
            val deferredRealtime = async {
                SunnyWeatherNetWork.getRealTimeWeather(lng, lat)
            }
            val deferredDaily = async {
                SunnyWeatherNetWork.getDailyWeather(lng, lat)
            }
            val realtimeResponse = deferredRealtime.await()
            val dailyResponse = deferredDaily.await()
            if (realtimeResponse.status == "ok" && dailyResponse.status == "ok") {
                val weather = Weather(realtimeResponse.result.realtime, dailyResponse.result.daily)
                Result.success(weather)
            } else {
                Result.failure(RuntimeException("real time status is ${realtimeResponse.status}" +
                        "daily time status is ${dailyResponse.status}"))
            }
        }
    }

    private fun <T> fire(context: CoroutineContext, block : suspend () -> Result<T>) = liveData<Result<T>>(context){
        val result = try {
            block()
        } catch (e : Exception) {
            Result.failure(e)
        }
        emit(result)
    }
}