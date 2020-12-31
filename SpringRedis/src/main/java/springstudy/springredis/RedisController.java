package springstudy.springredis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xwang on 12/28/20.
 */

@RestController
public class RedisController {

    @Autowired
    RedisSerive redisService;
    @Autowired
    RedisCacheService cacheService;

    @RequestMapping("/add")
    public String add(@RequestParam("id") String id, @RequestParam("value") String value) {
        redisService.add(id, value);
        return "";
    }

    @RequestMapping("/get")
    public String get(@RequestParam("id") String id) {
        return redisService.get(id);
    }

    @RequestMapping("/getSpringCache")
    public String getSpringCache(@RequestParam("id") String id) {
        return cacheService.get(id);
    }

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
