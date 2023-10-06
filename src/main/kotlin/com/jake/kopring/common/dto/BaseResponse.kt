package com.jake.kopring.common.dto

import com.jake.kopring.common.status.ResultCode

data class BaseResponse<T>(
    val resultCode: String = ResultCode.SUCCESS.name,
    val data: T? = null,
    val message: String =ResultCode.SUCCESS.msg
)