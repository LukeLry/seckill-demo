package org.seckill.dao;

import org.seckill.entity.SuccessKilled;

public interface SuccessKilledDao {

    /**
     * 插入购买明细表，可以过滤重复
     * @param seckillId
     * @param userPhone
     * @return 插入行数
     */
    int insertSuccessKilled(long seckillId, int userPhone);

    /**
     * ???有问题
     * @param seckillId
     * @param userPhone
     * @return
     */
    SuccessKilled queryByIdWithSeckill(long seckillId, int userPhone);
}
