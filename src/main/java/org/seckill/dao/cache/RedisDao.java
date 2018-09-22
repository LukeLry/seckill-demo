package org.seckill.dao.cache;

import org.seckill.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.*;

public class RedisDao {

    private final Logger logger = LoggerFactory.getLogger(RedisDao.class);

    private JedisPool jedisPool;

    public RedisDao(String ip, int port) {
        this.jedisPool = new JedisPool(ip, port);
    }

    public Seckill getSeckill(long seckillId) {
        try(Jedis jedis = jedisPool.getResource()) {
            String key = "seckillId" + seckillId;
            byte[] value = jedis.get(key.getBytes());
            if (value != null) {
                ByteArrayInputStream bais = new ByteArrayInputStream(value);
                ObjectInputStream ois = new ObjectInputStream(bais);
                logger.info("进入缓存寻找数据成功");
                return (Seckill) ois.readObject();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void putSeckill(Seckill seckill) {
        try(Jedis jedis = jedisPool.getResource()) {
            String key = "seckillId" + seckill.getSeckillId();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(seckill);
            byte[] value = baos.toByteArray();
            jedis.setex(key.getBytes(), 60 * 60, value);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
}
