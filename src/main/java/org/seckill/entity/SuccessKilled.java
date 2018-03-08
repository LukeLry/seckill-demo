package org.seckill.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SuccessKilled {

    private long seckillId;

    private int userPhone;

    private byte state;

    private Date createTime;

    private Seckill seckill;
}