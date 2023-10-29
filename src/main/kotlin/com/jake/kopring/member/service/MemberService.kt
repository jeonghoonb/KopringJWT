package com.jake.kopring.member.service

import com.jake.kopring.common.authority.JwtProvider
import com.jake.kopring.common.authority.TokenInfo
import com.jake.kopring.common.exception.InvalidInputException
import com.jake.kopring.common.status.ROLE
import com.jake.kopring.member.dto.LoginDto
import com.jake.kopring.member.dto.MemberDtoRequest
import com.jake.kopring.member.dto.MemberDtoResponse
import com.jake.kopring.member.entity.MemberRole
import com.jake.kopring.member.repository.MemberRepository
import com.jake.kopring.member.repository.MemberRoleRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.stereotype.Service

@Service
@Transactional
class MemberService(
    private val memberRepository: MemberRepository,
    private val memberRoleRepository: MemberRoleRepository,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
    private val jwtProvider: JwtProvider
) {

    /**
     * 회원가입
     */
    fun signUp(request: MemberDtoRequest): String {
        var member = memberRepository.findByMemberId(request.memberId)
        if (member != null) {
            throw InvalidInputException("id", "이미 등록된 ID 입니다.")
        }

        member = request.toEntity()
        memberRepository.save(member)

        val memberRole: MemberRole = MemberRole(null, ROLE.MEMBER, member)
        memberRoleRepository.save(memberRole)

        return "${member.name} 님, 회원가입이 완료되었습니다."
    }

    /**
     * 로그인
     */
    fun login(loginDto: LoginDto): TokenInfo {
        val authenticationToken = UsernamePasswordAuthenticationToken(loginDto.id, loginDto.password)
        val authentication = authenticationManagerBuilder.`object`.authenticate(authenticationToken)

        return jwtProvider.createToken(authentication)
    }

    /**
     * 내 정보 조회
     */
    fun searchMyInfo(id: Long): MemberDtoResponse {
        val member =
            memberRepository.findByIdOrNull(id) ?: throw InvalidInputException("id", "회원번호(${id})가 존재하지 않는 유저입니다.")
        return member.toDto()
    }

    /**
     * 내 정보 수정
     */
    fun saveMyInfo(memberDtoRequest: MemberDtoRequest): String {
        val member = memberDtoRequest.toEntity()
        memberRepository.save(member)
        return "${member.memberId} 님의 정보가 수정되었습니다."
    }
}