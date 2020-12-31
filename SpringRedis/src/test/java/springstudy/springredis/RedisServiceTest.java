package springstudy.springredis;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.LocalDateTime;

@SpringBootTest
public class RedisServiceTest {
    @Autowired
    RedisSerive redisSerive;

    @Test
    public void testWritePerformance() {
        LocalDateTime start = LocalDateTime.now();
        for (int i = 0; i < 1000000; i++) {
            String key = ""+i;
            String value = "" + i;
            redisSerive.add(key, value);
        }
        LocalDateTime end = LocalDateTime.now();
        System.out.println("ends in " + Duration.between(start, end).getSeconds());
    }

    @Test
    public void testReadPerformance() {
        LocalDateTime start = LocalDateTime.now();
        for (int i = 0; i < 100000; i++) {
            String key = "key" + i;
            redisSerive.get(key);
        }
        LocalDateTime end = LocalDateTime.now();
        System.out.println("ends in " + Duration.between(start, end).getSeconds());
    }
}
