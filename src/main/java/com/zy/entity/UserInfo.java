package com.zy.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.util.Date;

/**
 * @Description  
 * @Author  刘俊
 * @Date 2020-08-28 
 */

@Entity
@Getter
@Setter
@ToString(exclude = {})
@Accessors(chain = true)
@Table ( name ="user_info" )
public class UserInfo  {


	/** 用户id */
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer userId;

	/** 用户命名 */
	private String userName;

	/** 用户密码 */
	private String userPwd;

	/** 用户昵称 */
	private String userNick;

	/** 用户积分 */
	private Integer userIntegral;

	/** 用户等级 0-游客 1-普通用户 2-年会员 3-终身会员 */
	private String userType;

	/** 用户余额 */
	private Double userMoney;

	/** 注册时间 */
	private Date userRegTime;

	/** 会员到期时间 */
	private Date userExpirationTime;

	/** 用户头像地址 */
	private String userPhotoUrl;

	/** 用户手机号码 */
	private String userTelephone;

}
