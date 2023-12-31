package com.jake.kopring.member.repository

import com.jake.kopring.member.entity.Member
import com.jake.kopring.member.entity.MemberRole
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
    fun findByMemberId(memberId: String): Member?
}

interface MemberRoleRepository: JpaRepository<MemberRole, Long>