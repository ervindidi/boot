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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
	@NotNull(message = "用户密码不能为空",groups = {IRegister.class,IChangePwd.class})
	@NotEmpty(message = "用户密码不能为空",groups = {IRegister.class,IChangePwd.class})
	@Pattern(regexp = "^.{6,18}$", message = "用户密码为6-18位数字",groups = {IRegister.class,IChangePwd.class})
	private String userPwd;

	/** 用户昵称 */
	@NotNull(message = "用户昵称不能为空",groups = {IRegister.class})
	@NotEmpty(message = "用户昵称不能为空",groups = {IRegister.class})
	@Pattern(regexp = "^.{2,16}$", message = "用户昵称为2-16位数字",groups = {IRegister.class})
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
	@NotNull(message = "手机号码不能为空",groups = {IRegister.class,IChangePwd.class})
	@NotEmpty(message = "手机号码不能为空",groups = {IRegister.class,IChangePwd.class})
	@Pattern(regexp = "^\\d{11}$", message = "手机号码为11位数字",groups = {IRegister.class,IChangePwd.class})
	private String userTelephone;

	/** 推荐人id */
	private String upId;

	/** 城市 */
	private String city;

	/** 地区 */
	private String area;

	/** 学校 */
	private String school;

}
