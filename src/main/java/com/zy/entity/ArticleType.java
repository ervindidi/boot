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
import java.io.Serializable;

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
@Table ( name ="article_type" )
public class ArticleType implements Serializable {


	/** 文章类型id */
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer articleTypeId;

	/** 文章类型名字 */
	private String articleTypeName;

	/** 上级类型id */
	private Integer articleTypeUpid;

	/** 类型排名 */
	private Integer articleTypeRank;

}
