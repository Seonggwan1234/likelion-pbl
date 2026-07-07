package com.likelion.mission10.exception;

import org.springframework.http.HttpStatus;

public class MemberNotFoundException extends BusinessException {

    public MemberNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, "MEMBER_NOT_FOUND", "존재하지 않는 멤버입니다. id=" + id);
    }
}
