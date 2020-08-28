package com.zy.dao;

import com.zy.entity.ArticleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ArticleTypeDao extends BaseRepository<ArticleType,Integer>{
}
