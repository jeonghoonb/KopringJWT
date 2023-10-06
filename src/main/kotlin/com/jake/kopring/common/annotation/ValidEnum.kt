package com.jake.kopring.common.annotation

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.FIELD
import kotlin.reflect.KClass

@Target(FIELD)      // 어노테이션이 지정되어 사용할 종류를 정의
@Retention(RUNTIME) // 어노테이션을 컴파일된 클래스 파일에 저장할 것인지 러낱임에 반영할 것인지 정의
@MustBeDocumented   // API의 일부분으로 문서화하기 위해 사용
@Constraint(validatedBy = [ValidEnumValidator::class])
annotation class ValidEnum(
    val message: String = "Invalid enum value",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val enumClass: KClass<out Enum<*>>
)

class ValidEnumValidator : ConstraintValidator<ValidEnum, Any> {
    private lateinit var enumValues: Array<out Enum<*>>

    override fun initialize(annotation: ValidEnum) {
        enumValues = annotation.enumClass.java.enumConstants
    }

    override fun isValid(value: Any?, context: ConstraintValidatorContext?): Boolean {
        if (value == null) {
            return true
        }
        return enumValues.any { it.name == value.toString() }
    }

}