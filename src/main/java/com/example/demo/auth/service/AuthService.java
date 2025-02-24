package com.example.demo.auth.service;

import com.example.demo.auth.dto.request.AuthLoginRequestDto;
import com.example.demo.auth.dto.request.AuthSignupRequestDto;
import com.example.demo.auth.dto.response.AuthLoginResponseDto;
import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;

    @Transactional
    public void signup(AuthSignupRequestDto dto) {
        Member member = new Member(dto.getEmail());
        memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public AuthLoginResponseDto login(AuthLoginRequestDto dto) {
        Member member = memberRepository.findByEmail(dto.getEmail()).orElseThrow(
                ()-> new IllegalArgumentException("해당하는 멤버가 없습니다.")
        );
        return new AuthLoginResponseDto(member.getId());
    }
}
