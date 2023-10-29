package com.jake.kopring.member.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.jake.kopring.common.annotation.ValidEnum
import com.jake.kopring.common.status.Gender
import com.jake.kopring.member.entity.Member
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class MemberDtoRequest(
    var id: Long?,

    @field: NotBlank
    @JsonProperty("memberId")
    private val _memberId: String?,

    @field: NotBlank
    @field: Pattern(
        regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#\$%^&*])[a-zA-Z0-9!@#\$%^&*]{8,20}\$",
        message = "영문, 숫자, 특수문자를 포함한 8~20자리로 입력해주세요."
    )
    @JsonProperty("password")
    private val _password: String?,

    @field: NotBlank
    @JsonProperty("name")
    private val _name: String?,

    @field: NotBlank
    @field: Pattern(
        regexp = "^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$",
        message = "날짜형식(YYYY-MM-DD)을 확인해주세요."
    )
    @JsonProperty("birthDate")
    private val _birthDate: String?,

    @field: NotBlank
    @field: ValidEnum(enumClass = Gender::class, message = "MAN or WOMAN 중 하나를 선택해주세요.")
    @JsonProperty("gender")
    private val _gender: String?,

    @field: NotBlank
    @field: Email
    @JsonProperty("email")
    private val _email: String?
) {
    val memberId: String
        get() = _memberId!!

    val password: String
        get() = _password!!

    val name: String
        get() = _name!!

    val birthDate: LocalDate
        get() = _birthDate!!.toLocalDate()

    val gender: Gender
        get() = Gender.valueOf(_gender!!)

    val email: String
        get() = _email!!

    // String to LocalDate 변환을 위해 확장 함수 선언
    private fun String.toLocalDate(): LocalDate =
        LocalDate.parse(this, DateTimeFormatter.ofPattern("yyyy-MM-dd"))

    fun toEntity(): Member =
        Member(id, memberId, password, name, birthDate, gender, email)
}

data class LoginDto(
    @field:NotBlank
    @JsonProperty("memberId")
    private val _memberId: String?,

    @field:NotBlank
    @JsonProperty("password")
    private val _password: String?,
){
    val id: String
        get() = _memberId!!
    val password: String
        get() = _password!!
}

data class MemberDtoResponse(
    val id: Long,
    val memberId: String,
    val name: String,
    val birthDate: String,
    val gender: String,
    val email: String
)