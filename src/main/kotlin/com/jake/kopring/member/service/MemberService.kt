package com.jake.kopring.member.service

import com.jake.kopring.common.exception.InvalidInputException
import com.jake.kopring.common.status.ROLE
import com.jake.kopring.member.dto.MemberDtoRequest
import com.jake.kopring.member.entity.Member
import com.jake.kopring.member.entity.MemberRole
import com.jake.kopring.member.repository.MemberRepository
import com.jake.kopring.member.repository.MemberRoleRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class MemberService(
    private val memberRepository: MemberRepository,
    private val memberRoleRepository: MemberRoleRepository
) {

    /**
     * 회원가입
     */
    fun signUp(request: MemberDtoRequest): String {
        var member = memberRepository.findByMemberId(request.id)
        if (member != null) {
            throw InvalidInputException("id", "이미 등록된 ID 입니다.")
        }

        member = request.toEntity()
        memberRepository.save(member)

        val memberRole: MemberRole = MemberRole(null, ROLE.MEMBER, member)
        memberRoleRepository.save(memberRole)

        return "${member.name} 님, 회원가입이 완료되었습니다."
    }
}