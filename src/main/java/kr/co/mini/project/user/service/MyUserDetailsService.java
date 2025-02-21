package kr.co.mini.project.user.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import kr.co.mini.project.user.entity.User;

import java.util.Collection;
import java.util.Collections;

public class MyUserDetailsService implements UserDetails {
    // VO에서 가져오는건 일단 문제가 있음 일단 메모리로 하는거라 보류 함
    // 개발방법도 일단은 모르겠음
    private final User user;

    public MyUserDetailsService(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return user.getPassword(); // 비밀번호
    }

    @Override
    public String getUsername() {
        return user.getName(); // 사용자 이름
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // 계정 만료 여부
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // 계정 잠금 여부
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 자격 증명 만료 여부
    }

    @Override
    public boolean isEnabled() {
        return true; // 계정 활성화 여부
    }

    public Integer getId() {
        return user.getId(); // 사용자 ID
    }
} 