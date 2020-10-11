package com.zy.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
@Entity
public class IntegralDetail implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private Integer user_id;
    //积分变化类型： 1-看广告得积分  2-抽奖消费积分
    private String integral_type;
    //积分变换详情
    private String integral_msg;
    //积分余额
    private Integer integral;
    private Date time;
}
