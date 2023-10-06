package com.jake.kopring.member.service

import com.jake.kopring.member.dto.MemberDtoRequest
import com.jake.kopring.member.entity.Member
import com.jake.kopring.member.repository.MemberRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class MemberService(
    private val memberRepository: MemberRepository
) {

    /**
     * 회원가입
     */
    fun signUp(request: MemberDtoRequest): String {
        var member = memberRepository.findByMemberId(request.id)
        if (member != null) {
            return "이미 등록된 ID 입니다."
        }

        member = Member(
            null,
            request.id,
            request.password,
            request.name,
            request.birthDate,
            request.gender,
            request.email
        )

        memberRepository.save(member)

        return "${member.name} 님, 회원가입이 완료되었습니다."
    }
}