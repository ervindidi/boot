package com.zy.dao;

import com.zy.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

public interface ArticleDao extends BaseRepository<Article,Integer>{
}
