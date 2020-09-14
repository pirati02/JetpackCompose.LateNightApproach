package ge.baqar.myapplication.data.dto.arch

import com.google.gson.annotations.SerializedName


data class RequestBase<T>(@field:SerializedName("value")
                          val value: T) : HeaderBaseRequest()

