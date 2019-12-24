package com.oa.desktop.news.newsLabel.service;

import com.oa.commons.beans.News;

import java.util.List;

public interface NewsService {

    int newsAdd(News news);

    List<News> queryNewsAll();

    int newsDelete(Integer[] ids);

    List<News> queryNewsByDate(String format, String format1);
}
