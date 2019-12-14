package com.oa.desktop.news.newsLabel.Dao;

import com.oa.commons.beans.News;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsDao {
    int newsAdd(News news);

    List<News> queryNewsAll();
}
