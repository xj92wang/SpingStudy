package springstudy.springredis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;

import java.time.Duration;
import java.time.LocalDateTime;

@SpringBootTest
public class RedisCacheServiceTest {

    @Autowired
    RedisCacheService redisCacheService;

    @Test
    public void testCachePerformance() {
        LocalDateTime start = LocalDateTime.now();
        for (int i = 0; i < 100; i++) {
            String key = "" + i;
            redisCacheService.get(key);
        }
        LocalDateTime end = LocalDateTime.now();
        System.out.println("ends in " + Duration.between(start, end).getSeconds());
    }
}
