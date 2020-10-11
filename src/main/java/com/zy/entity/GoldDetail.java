package com.zy.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.naming.Name;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
@Entity
public class GoldDetail implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private Integer user_id;
    //金豆变换类型 1-抽奖得金豆  2-金豆兑换商品
    private String gold_type;
    //金豆变化详情
    private String gold_msg;
    //金豆余额
    private Integer gold;
    private Date time;
}
