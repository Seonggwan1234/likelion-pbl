package com.likelion.mission10.exception;

import org.springframework.http.HttpStatus;

public class AssignmentNotFoundException extends BusinessException {

    public AssignmentNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, "ASSIGNMENT_NOT_FOUND", "존재하지 않는 과제입니다. id=" + id);
    }
}
