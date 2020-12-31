package springstudy.springredis;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class RedisCacheService {
    @Cacheable(cacheNames = "mycache")
    public String get(String key){
        //System.out.println("Not from cache" + key);
        return "value"+key;
    }
}
