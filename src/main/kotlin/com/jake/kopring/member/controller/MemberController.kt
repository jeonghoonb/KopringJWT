package com.jake.kopring.member.controller

import com.jake.kopring.member.dto.MemberDtoRequest
import com.jake.kopring.member.service.MemberService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/member")
class MemberController(
    private val memberService: MemberService
) {

    /**
     * 회원가입
     */
    @PostMapping("/signup")
    fun signUp(@RequestBody request: MemberDtoRequest): String {
        return memberService.signUp(request)
    }
}