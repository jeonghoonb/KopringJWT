package com.jake.kopring.member.dto

import com.jake.kopring.common.status.Gender
import java.time.LocalDate

data class MemberDtoRequest(
    val no: Long?,
    val id: String,
    val password: String,
    val name: String,
    val birthDate: LocalDate,
    val gender: Gender,
    val email: String
)