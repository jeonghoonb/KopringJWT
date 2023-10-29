package com.jake.kopring.member.controller

import com.jake.kopring.common.authority.TokenInfo
import com.jake.kopring.common.dto.BaseResponse
import com.jake.kopring.common.dto.CustomUser
import com.jake.kopring.member.dto.LoginDto
import com.jake.kopring.member.dto.MemberDtoRequest
import com.jake.kopring.member.dto.MemberDtoResponse
import com.jake.kopring.member.service.MemberService
import jakarta.validation.Valid
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/member")
class MemberController(
    private val memberService: MemberService
) {

    /**
     * 회원가입
     */
    @PostMapping("/signup")
    fun signUp(@RequestBody @Valid request: MemberDtoRequest): BaseResponse<Unit> {
        val resultMsg: String = memberService.signUp(request)
        return BaseResponse(message = resultMsg)
    }

    /**
     * 로그인
     */
    @PostMapping("/login")
    fun login(@RequestBody @Valid loginDto: LoginDto): BaseResponse<TokenInfo> {
        val tokenInfo = memberService.login(loginDto)
        return BaseResponse(data = tokenInfo)
    }

    /**
     * 내 정보 조회
     */
    @GetMapping("/info")
    fun searchMyInfo(): BaseResponse<MemberDtoResponse> {
        val id = (SecurityContextHolder.getContext().authentication.principal as CustomUser).userId
        val response = memberService.searchMyInfo(id)
        return BaseResponse(data = response)
    }

    /**
     * 내 정보 수정
     */
    @PutMapping("/info")
    fun saveMyInfo(@RequestBody @Valid memberDtoRequest: MemberDtoRequest): BaseResponse<Unit> {
        val id = (SecurityContextHolder.getContext().authentication.principal as CustomUser).userId
        memberDtoRequest.id = id
        val resultMsg: String = memberService.saveMyInfo(memberDtoRequest)
        return BaseResponse(message = resultMsg)
    }
}