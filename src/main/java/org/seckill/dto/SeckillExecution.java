package org.seckill.dto;

import lombok.Data;
import org.seckill.entity.SuccessKilled;

@Data
public class SeckillExecution {

    private long seckillId;

    private byte state;

    private String stateInfo;

    private SuccessKilled successKilled;

    public SeckillExecution(long seckillId, byte state, String stateInfo, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.state = state;
        this.stateInfo = stateInfo;
        this.successKilled = successKilled;
    }

    public SeckillExecution(long seckillId, byte state, String stateInfo) {
        this.seckillId = seckillId;
        this.state = state;
        this.stateInfo = stateInfo;
    }
}
