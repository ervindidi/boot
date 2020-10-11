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
@Table ( name ="article" )
public class Article  implements Serializable {


	/** 文字id */
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer articleId;

	/** 文章标题 */
	private String articleTitle;

	/** 视频在bilibli上的地址 */
	private String articleVideoBilibli;

	/** 视频url地址 */
	private String articleVideoUrl;

	/** 文章主图地址 */
	private String articleImageUrl;

	/** 文字关键字 */
	private String articleKeywords;

	/** 文字描述 */
	private String articleDescription;

	/** 文章内容 */
	private String articleContent;

	/** 文字点赞数 */
	private Integer articleLikeNum;

	/** 文字评论数量 */
	private Integer articleCommentNum;

	/** 文字发布时间 */
	private Date articlePublishTime;

	/** 内容下载链接 */
	private String articleDownloadUrl;

	/** 下载所需积分 */
	private Integer articleDownloadIntegral;

	/** 用户id */
	private Integer userId;

	/** 文章类型id */
	private Integer articleTypeId;

	private Integer askNum;

	private Integer homeworkNum;

	/** 文章排名 */
	private Integer articleRankNum;

}
