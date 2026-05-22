package Mission05.config;

import Mission05.domain.BackendLion;
import Mission05.domain.DesignLion;
import Mission05.domain.FrontendLion;
import Mission05.domain.Lion;
import Mission05.repository.LionRepository;
import Mission05.repository.MemoryLionRepository;
import Mission05.repository.MockLionRepository;
import Mission05.service.LionService;

import java.util.Arrays;
import java.util.List;

public class AppConfig {

    // MemoryRepository 기반 Service 조립
    public LionService lionService() {
        return new LionService(lionRepository());
    }

    public LionRepository lionRepository() {
        return new MemoryLionRepository();
    }

    // MockRepository 기반 Service 조립 (사전 정의된 테스트 데이터 사용)
    public LionService mockLionService() {
        List<Lion> mockData = Arrays.asList(
            new BackendLion("Mock백엔드", "소프트웨어학", 99),
            new FrontendLion("Mock프론트", "경영학", 99),
            new DesignLion("Mock디자인", "시각디자인학", 99)
        );
        return new LionService(new MockLionRepository(mockData));
    }
}
