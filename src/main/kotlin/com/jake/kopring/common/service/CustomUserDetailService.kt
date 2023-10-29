package com.jake.kopring.common.service

import com.jake.kopring.common.dto.CustomUser
import com.jake.kopring.member.entity.Member
import com.jake.kopring.member.repository.MemberRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class CustomUserDetailService(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails =
        memberRepository.findByMemberId(username)
            ?.let { createUserDetails(it) } ?: throw UsernameNotFoundException("${username} 유저는 없습니다.")


    private fun createUserDetails(member: Member): UserDetails =
        CustomUser(
            member.id!!,
            member.memberId,
            passwordEncoder.encode(member.password),
            member.memberRole!!.map { SimpleGrantedAuthority("ROLE_${it.role}") }
        )
}