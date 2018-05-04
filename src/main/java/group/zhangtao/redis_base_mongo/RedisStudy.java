package group.zhangtao.redis_base_mongo;

import redis.clients.jedis.Jedis;

public class RedisStudy {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        jedis.set("tao","张涛");
        System.out.println( jedis.get("tao"));

    }
}
