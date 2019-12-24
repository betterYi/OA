package com.oa.desktop.news.newsLabel.Dao;

import com.oa.commons.beans.News;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsDao {
    int newsAdd(News news);

    List<News> queryNewsAll();

    int newsDelete(Integer[] ids);

    List<News> queryNewsByDate(@Param("startTime") String startTime,@Param("endTime")String endTime);
}
