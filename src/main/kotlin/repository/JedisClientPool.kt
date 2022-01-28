package repository

import redis.clients.jedis.JedisPool

object JedisClientPool {
    val jedisPool = JedisPool("10.147.1.1", 16379)
}