package com.likelion.mission06.config;

import com.likelion.mission06.domain.BackendLion;
import com.likelion.mission06.domain.DesignLion;
import com.likelion.mission06.domain.FrontendLion;
import com.likelion.mission06.service.LionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    // 애플리케이션 시작 시 초기 데이터를 등록하는 Bean
    @Bean
    public CommandLineRunner initData(LionService lionService) {
        return args -> {
            System.out.println("=== [초기 데이터 등록] ===");
            lionService.register(new BackendLion("김성관", "컴퓨터공학", 13));
            lionService.register(new FrontendLion("정선호", "컴퓨터공학", 13));
            lionService.register(new DesignLion("김건호", "컴퓨터공학", 13));
            lionService.register(new BackendLion("김나나", "소프트웨어학", 13));
            lionService.register(new BackendLion("김성관", "컴퓨터공학", 13)); // 중복 테스트
            System.out.println("=== [초기 데이터 등록 완료] ===");
        };
    }
}
