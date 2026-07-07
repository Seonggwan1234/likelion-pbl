package com.likelion.mission10.exception;

import org.springframework.http.HttpStatus;

public class TeamNotFoundException extends BusinessException {

    public TeamNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, "TEAM_NOT_FOUND", "존재하지 않는 팀입니다. id=" + id);
    }
}
