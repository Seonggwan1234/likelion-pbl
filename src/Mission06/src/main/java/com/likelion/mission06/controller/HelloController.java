package com.likelion.mission06.controller;

import com.likelion.mission06.domain.Lion;
import com.likelion.mission06.service.LionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class HelloController {

    private final LionService lionService;

    public HelloController(LionService lionService) {
        this.lionService = lionService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "안녕하세요! 멋쟁이사자처럼 13기 Spring Boot에 오신 것을 환영합니다!";
    }

    @GetMapping("/lions")
    public String lions() {
        List<Lion> all = lionService.findAll();
        if (all.isEmpty()) {
            return "등록된 멤버가 없습니다.";
        }
        return all.stream()
                .map(Lion::getInfo)
                .collect(Collectors.joining("\n"));
    }

    @GetMapping("/lions/part")
    public String lionsByPart(@RequestParam String part) {
        List<Lion> list = lionService.findByPart(part);
        if (list.isEmpty()) {
            return "[" + part + "] 파트 멤버가 없습니다.";
        }
        return list.stream()
                .map(Lion::getInfo)
                .collect(Collectors.joining("\n"));
    }
}
