import redis.clients.jedis.JedisPool

class Test {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val jedisPool = JedisPool("10.147.1.1", 6379)
            jedisPool.resource.use {
                val resp = it.get("splendor_room_")

            }
        }
    }
}